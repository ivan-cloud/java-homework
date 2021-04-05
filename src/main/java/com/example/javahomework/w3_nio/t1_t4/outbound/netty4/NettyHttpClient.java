package com.example.javahomework.w3_nio.t1_t4.outbound.netty4;//package io.github.kimmking.gateway.outbound;

import com.example.javahomework.w3_nio.t1_t4.filter.HeaderHttpResponseFilter;
import com.example.javahomework.w3_nio.t1_t4.filter.HttpRequestFilter;
import com.example.javahomework.w3_nio.t1_t4.filter.HttpResponseFilter;
import com.example.javahomework.w3_nio.t1_t4.outbound.httpclient4.NamedThreadFactory;
import com.example.javahomework.w3_nio.t1_t4.router.HttpEndpointRouter;
import com.example.javahomework.w3_nio.t1_t4.router.RandomHttpEndpointRouter;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpVersion;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

// 2.（选做）使用 netty 实现后端 http 访问（代替上一步骤）
public class NettyHttpClient {
    private List<String> backendUrls;
    private ExecutorService proxyService;
    HttpResponseFilter filter = new HeaderHttpResponseFilter();
    HttpEndpointRouter router = new RandomHttpEndpointRouter();

    public NettyHttpClient(List<String> backends) {
        this.backendUrls = backends.stream().map(this::formatUrl).collect(Collectors.toList());
        int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();//.DiscardPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);
    }

    private String formatUrl(String backend) {
        return backend.endsWith("/") ? backend.substring(0, backend.length() - 1) : backend;
    }


    public void connect(ChannelHandlerContext ctx, String host, int port, String path) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
                    ch.pipeline().addLast(new HttpResponseDecoder());
                    // 客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
                    ch.pipeline().addLast(new HttpRequestEncoder());
                    ch.pipeline().addLast(new NettyHttpClientOutboundHandler(ctx));
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync();

            String msg = "Are you ok?";
            DefaultFullHttpRequest httpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET,
                    path, Unpooled.wrappedBuffer(msg.getBytes("UTF-8")));

            // 构建http请求
            httpRequest.headers().set(HttpHeaders.Names.HOST, host);
            httpRequest.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
            httpRequest.headers().set(HttpHeaders.Names.CONTENT_LENGTH, httpRequest.content().readableBytes());
            // 发送http请求
            f.channel().write(httpRequest);
            f.channel().flush();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }

    public void handler(FullHttpRequest fullRequest, ChannelHandlerContext ctx, HttpRequestFilter filter) {
        String backendUrl = router.route(this.backendUrls);
        final String url = backendUrl + fullRequest.uri();
        filter.filter(fullRequest, ctx);
        proxyService.submit(() -> fetchGet(ctx, url));
    }

    private void fetchGet(ChannelHandlerContext ctx, String url) {
        URI uri = null;
        try {
            uri = new URI(url);
            this.connect(ctx, uri.getHost(), uri.getPort(), uri.getPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}