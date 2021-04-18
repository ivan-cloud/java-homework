package com.example.javahomework.w5_fx.t4_1;


import com.example.javahomework.w5_fx.t4_1.inbound.HttpInboundServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class NettyServerApplication {

    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "3.0.0";

    public static void main(String[] args) {
        SpringApplication.run(NettyServerApplication.class, args);
    }

    @Component
    public class MyApplicationRunner implements ApplicationRunner {

        @Autowired
        HttpInboundServer server;

        @Override
        public void run(ApplicationArguments args) throws Exception {

            System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " starting...");
            System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " started at http://localhost:" + server.getPort() + " for server:" + server.toString());
            try {
                server.run();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
