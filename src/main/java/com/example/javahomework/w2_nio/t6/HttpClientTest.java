package com.example.javahomework.w2_nio.t6;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HttpClientTest {
    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8801";
        String body = "{}";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final HttpPost request = new HttpPost(url);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(body, StandardCharsets.UTF_8));
            HttpResponse response = httpClient.execute(request);
            System.out.println(EntityUtils.toString(response.getEntity()));
        }
    }
}
