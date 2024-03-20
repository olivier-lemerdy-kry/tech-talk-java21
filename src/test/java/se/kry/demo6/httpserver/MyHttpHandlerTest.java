package se.kry.demo6.httpserver;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class MyHttpHandlerTest {

    private static final int PORT = 23456;

    private HttpServer server;

    @BeforeEach
    void before() throws IOException {
        server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/my-path", new MyHttpHandler());
        server.start();
    }

    @Test
    void request_returns_handler_content() throws URISyntaxException, IOException, InterruptedException {
        // Given a request
        var request = HttpRequest.newBuilder(new URI("http://localhost:" + PORT + "/my-path")).GET().build();
        // And an HTTP client
        try (var client = HttpClient.newHttpClient()) {
            // When the client sends the request and receives a response
            var responseBody = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            // The response contains the content of index.html
            assertThat(responseBody).isEqualTo("Welcome to Java 21 Simple HTTP Server");
        }
    }

    @AfterEach
    void after() {
        server.stop(1);
    }
}
