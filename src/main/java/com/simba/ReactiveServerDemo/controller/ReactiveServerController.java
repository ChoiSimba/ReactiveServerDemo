package com.simba.ReactiveServerDemo.controller;

import com.simba.ReactiveServerDemo.model.Book;
import com.simba.ReactiveServerDemo.service.ReactiveServerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReactiveServerController {
    private ReactiveServerService reactiveServerService;


    @Autowired
    ReactiveServerController(ReactiveServerService reactiveServerService) {
        this.reactiveServerService = reactiveServerService;
    }

    /**
     * Mono 동기
     */
    @GetMapping("/mono/sync/{delayMs}")
    public Book getMonoSync(@PathVariable("delayMs") int delayMs) {
        return reactiveServerService.getMonoSync(delayMs);
    }

    /**
     * Mono 비동기
     */
    @GetMapping("/mono/async/{delayMs}")
    public Mono<Book> getMonoAsync(@PathVariable("delayMs") int delayMs) {
        return reactiveServerService.getMonoAsync(delayMs);
    }

    /**
     * Flux 비동기
     */
    @GetMapping("/flux")
    public Flux<Book> getFlux() {
        return reactiveServerService.getFlux();
    }

    /**
     * Gzip
     */
    @GetMapping("/gzipContent/{delayMs}")
    public Mono<byte[]> getGzipContent(@PathVariable("delayMs") int delayMs) {
        return reactiveServerService.getGzipContent(delayMs);
    }

}
