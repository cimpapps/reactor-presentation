package com.example.reactor.controller;

import com.example.reactor.model.Person;
import com.example.reactor.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;


@RestController
public class PersonController {


    @Autowired
    private PersonRepo personRepo;


    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Person> all() {
        return personRepo.findAll()
                .zipWith(Flux.interval(Duration.ofSeconds(1)))
                .map(Tuple2::getT1);
    }

    @PostMapping("/add")
    public Mono<Person> add(@RequestBody Person person) {
        return personRepo.save(person);

    }


}
