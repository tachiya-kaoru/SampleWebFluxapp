package com.example.samplewebfluxapp;

import org.springframework.stereotype.Controller;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import org.springframework.web.reactive.function.server.ServerRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import reactor.core.publisher.Mono;

@Controller
public class SampleController {
   
    @Bean
    public RouterFunction<ServerResponse> routes() {
        return route(GET("/f/hello"), this::hello);
    }

    Mono<ServerResponse> hello(ServerRequest req) {
        return ok().body(Mono.just("Hello Functional routing world!"), String.class);
    }
}