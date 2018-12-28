package com.simba.ReactiveServerDemo.handler;

import com.simba.ReactiveServerDemo.model.Book;
import com.simba.ReactiveServerDemo.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class BookHandler {
    private final BookService bookService;


    @Autowired
    BookHandler(BookService bookService) {
        this.bookService = bookService;
    }


    public Mono<ServerResponse> book(ServerRequest request) {
        int bookId = Integer.valueOf(request.pathVariable("bookId"));

        return ServerResponse.ok().body(bookService.getBook(bookId), Book.class);
    }

    public Mono<ServerResponse> books(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(bookService.getBooks(), Book.class);
    }

}
