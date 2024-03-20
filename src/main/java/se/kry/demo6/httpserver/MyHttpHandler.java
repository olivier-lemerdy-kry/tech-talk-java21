package se.kry.demo6.httpserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class MyHttpHandler implements HttpHandler {

    private static final String MESSAGE = "Welcome to Java 21 Simple HTTP Server";

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            try (OutputStream outputStream = exchange.getResponseBody()) {
                exchange.sendResponseHeaders(200, MESSAGE.length());
                outputStream.write(MESSAGE.getBytes());
                outputStream.flush();
            }
        }
    }
}
