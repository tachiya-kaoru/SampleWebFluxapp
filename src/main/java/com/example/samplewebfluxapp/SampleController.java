package com.example.samplewebfluxapp;

import org.springframework.stereotype.Controller;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import org.springframework.web.reactive.function.server.ServerRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.result.view.Rendering;

import org.springframework.http.MediaType;

import reactor.core.publisher.Mono;

@Controller
public class SampleController {

    @RequestMapping("/f/flux")
    Mono<Rendering> flux() {
        return Mono.just(Rendering.view("flux").build());
    }
    @Bean
    public RouterFunction<ServerResponse> routes() {
        return route(GET("/f/hello"), this::hello)
        .andRoute(GET("/f/hello2"), this::hello2)
        .andRoute(GET("/f/flux2"), this::flux2);
    }

    Mono<ServerResponse> flux2(ServerRequest req) {
        return ok().contentType(MediaType.TEXT_HTML).render("flux");
    }

    Mono<ServerResponse> hello(ServerRequest req) {
        return ok().body(Mono.just("Hello Functional routing world!"), String.class);
    }

    Mono<ServerResponse> hello2(ServerRequest req) {
        return ok().body(Mono.just("関数ルーティングの世界へようこそ!"), String.class);
    }
}