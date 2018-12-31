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

    @GetMapping("/book/{bookIndex}")
    public Mono<Book> getBook(@PathVariable("bookIndex") int bookIndex) {
        return bookService.getMono(bookIndex);
    }

    @GetMapping("/bookBlocking/{bookIndex}")
    public Book getBookBlocking(@PathVariable("bookIndex") int bookIndex) {
        return bookService.getMonoBlocking(bookIndex);
    }

    @GetMapping("/books")
    public Flux<Book> getBooks() {
        return bookService.getFlux();
    }

}
