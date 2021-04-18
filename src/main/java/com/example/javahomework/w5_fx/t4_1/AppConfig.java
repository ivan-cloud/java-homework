package com.example.javahomework.w5_fx.t4_1;

import com.example.javahomework.w5_fx.t4_1.inbound.HttpInboundHandler;
import com.example.javahomework.w5_fx.t4_1.inbound.HttpInboundInitializer;
import com.example.javahomework.w5_fx.t4_1.inbound.HttpInboundServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {
    @Value("${proxyPort:8888}")
    int proxyPort;
    @Value("${proxyServers:http://localhost:8808}")
    List<String> proxyServers;

    @Bean
    public HttpInboundServer httpInboundServer() {
        return new HttpInboundServer(this.proxyPort, this.proxyServers);
    }

    @Bean
    public HttpInboundInitializer httpInboundInitializer() {
        return new HttpInboundInitializer(this.proxyServers);
    }

    @Bean
    public HttpInboundHandler httpInboundHandler() {
        return new HttpInboundHandler(this.proxyServers);
    }
}
