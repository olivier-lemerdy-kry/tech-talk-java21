package se.kry.demo1.vthreads;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public record ThreadsRunner(int count, ExecutorService executor) implements Runnable {

    public static void createPlatformThreadsAndRun(int count) {
        try (var executor = Executors.newCachedThreadPool()) {
            new ThreadsRunner(count, executor).run();
        }
    }

    public static void createVirtualThreadsAndRun(int count) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            new ThreadsRunner(count, executor).run();
        }
    }

    @Override
    public void run() {
        // Given a number of platform threads
        IntStream.range(0, count).forEach(i ->
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    return i;
                }));
    }
}
