package com.simba.ReactiveServerDemo.config;

import com.simba.ReactiveServerDemo.handler.BookHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@EnableWebFlux
public class WebConfig implements WebFluxConfigurer {
    private final BookHandler bookHandler;


    @Autowired
    WebConfig(BookHandler bookHandler) {
        this.bookHandler = bookHandler;
    }

    @Bean
    public RouterFunction<?> routeBook() {
        return route(GET("/book/{bookId}").and(accept(APPLICATION_JSON)), bookHandler::book);
    }

    @Bean
    public RouterFunction<?> routeBooks() {
        return route(GET("/books").and(accept(APPLICATION_STREAM_JSON)), bookHandler::books);
    }

}
