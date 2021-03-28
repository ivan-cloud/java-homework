package com.example.javahomework.w2_nio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer01 {

    public static void main(String[] args) throws IOException {
        System.out.println("server starting...");
        ServerSocket serverSocket = new ServerSocket(8801);
        while (true) {
            try (Socket socket = serverSocket.accept()) {
                service(socket);
            }
        }
    }

    private static void service(Socket socket) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true)) {
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello, nio1";
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
        }
    }
}
