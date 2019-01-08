package com.simba.ReactiveServerDemo.service;

import com.simba.ReactiveServerDemo.model.Book;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveServerService {
    private final List<Book> books;
    private final byte[] gzipTestContent;


    /**
     * 임시 테스트데이터
     */
    ReactiveServerService() {
        books = Arrays.asList(
                new Book("Spring 5", "인사이트", 50_000),
                new Book("Spring Boot 2", "홍길동", 20_000),
                new Book("Java 8", "위메프", 80_000)
        );

        try {
            gzipTestContent = StreamUtils.copyToByteArray(new ClassPathResource("static/gzipTestContent.txt.gz").getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Mono 동기
     */
    public Book getMonoSync(int delayMs) {
        return getMonoAsync(delayMs).block();

    }

    /**
     * Mono 비동기
     */
    public Mono<Book> getMonoAsync(int delayMs) {
        return Mono.fromSupplier(() -> books.get(0))
                .delayElement(Duration.ofMillis(delayMs))
                .log()
                ;
    }

    /**
     * Flux 비동기
     */
    public Flux<Book> getFlux() {
        return Flux.fromIterable(books)
                .delayElements(Duration.ofSeconds(1))
                .log()
                ;
    }

    /**
     * Gzip
     */
    public Mono<byte[]> getGzipContent(int delayMs) {
        return Mono.fromSupplier(() -> gzipTestContent)
                .delayElement(Duration.ofMillis(delayMs))
                .log()
                ;
    }

}
