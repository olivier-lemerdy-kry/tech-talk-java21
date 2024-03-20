package se.kry.demo6.httpserver;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.SimpleFileServer;
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
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleFileServerTest {

    private static final int PORT = 12345;

    private HttpServer server;

    @BeforeEach
    void before() throws URISyntaxException {
        // Why is it so difficult to resolve a path relative to the project in test XD ?
        var httpServerResourcesPath = Path.of(ClassLoader.getSystemResource("").toURI())
                .getParent().getParent().getParent()
                .resolve("resources/test/se/kry/demo6/httpserver");
        // Given a server serving files from test resources
        server = SimpleFileServer.createFileServer(
                new InetSocketAddress(PORT),
                httpServerResourcesPath,
                SimpleFileServer.OutputLevel.VERBOSE
        );
        // When we start it
        server.start();
    }

    @Test
    void request_returns_index_content() throws URISyntaxException, IOException, InterruptedException {
        // Given a request
        var request = HttpRequest.newBuilder(new URI("http://localhost:" + PORT)).GET().build();
        // And an HTTP client
        try (var client = HttpClient.newHttpClient()) {
            // When the client sends the request and receives a response
            var responseBody = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            // The response contains the content of index.html
            assertThat(responseBody).isEqualTo("Welcome to Java 21 Simple File Server");
        }
    }

    @AfterEach
    void after() {
        server.stop(1);
    }
}
