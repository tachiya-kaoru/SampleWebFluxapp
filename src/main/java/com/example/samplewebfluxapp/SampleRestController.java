package com.example.samplewebfluxapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



@RestController

public class SampleRestController {

    @RequestMapping("/")
    public String hello() {
        return "Hello, Flux !";
    }

    @RequestMapping("/flux")
    public Mono<String> flux() {
        return Mono.just("Hello, Flux (Mono) .");
    }

    @RequestMapping("/flux2")
    public Flux<String> flux2() {
        return Flux.just("Hello Flux. ","これはFluxのサンプルです。");
    }

    @Autowired
    PostRepository repository;

    @RequestMapping("/post")
    public Mono<Post> post() {
        Post post = new Post(0,0,"dummy","dummy message...");
        return Mono.just(post);
    }

    @RequestMapping("/post/{id}")
    public Mono<Post> post(@PathVariable int id) {
        Post post = repository.findById(id);
        return Mono.just(post);
    }

    @RequestMapping("/posts")
    public Flux<Object> posts() {
        List<Post> posts = repository.findAll();
        return Flux.fromArray(posts.toArray());
    }
    @RequestMapping("/file")
    public Mono<String> file() {
        String result = "";
        try {
            ClassPathResource cr = new ClassPathResource("sample.txt");
            InputStream is = cr.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }            
        } catch (IOException e) {
            result = e.getMessage();
        }
        return Mono.just(result);
    }

    @PostConstruct
    public void init() {
        Post p1 = new Post(1,1,"Hello", "Hello FLux!");
        Post p2 = new Post(2,2,"Sample", "This is sample post");
        Post p3 = new Post(3,3,"ハロー", "これはサンプルです。");
        repository.saveAndFlush(p1);
        repository.saveAndFlush(p2);
        repository.saveAndFlush(p3);
    }

   
}
