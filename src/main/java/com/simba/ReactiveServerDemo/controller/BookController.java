package com.simba.ReactiveServerDemo.controller;

import com.simba.ReactiveServerDemo.model.Book;
import com.simba.ReactiveServerDemo.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class BookController {
    private BookService bookService;

    @Autowired
    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Mono 비동기
     */
    @GetMapping("/bookAsync/{bookIndex}")
    public Mono<Book> getBookAsync(@PathVariable("bookIndex") int bookIndex) {
        return bookService.getMonoAsync(bookIndex);
    }

    /**
     * Mono 동기
     */
    @GetMapping("/bookSync/{bookIndex}")
    public Book getBookSync(@PathVariable("bookIndex") int bookIndex) {
        return bookService.getMonoSync(bookIndex);
    }

    /**
     * Flux 비동기
     */
    @GetMapping("/books")
    public Flux<Book> getBooks() {
        return bookService.getFlux();
    }

}
