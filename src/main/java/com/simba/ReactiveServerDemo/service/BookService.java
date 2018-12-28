package com.simba.ReactiveServerDemo.service;

import com.simba.ReactiveServerDemo.model.Book;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {
    private List<Book> books;


    BookService() {
        books = Arrays.asList(
                new Book("Spring 5", "인사이트", 50_000),
                new Book("Spring Boot 2", "홍길동", 20_000),
                new Book("Java 8", "위메프", 80_000)
        );
    }

    public Mono<Book> getBook(int bookIndex) {
        return Mono.fromSupplier(() -> books.get(bookIndex))
                .delayElement(Duration.ofSeconds(2))
                .log()
                ;
    }

    public Flux<Book> getBooks() {
        return Flux.fromIterable(books)
                .delayElements(Duration.ofSeconds(1))
                .log()
                ;
    }
}
