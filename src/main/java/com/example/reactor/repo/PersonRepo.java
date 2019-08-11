package com.example.reactor.repo;

import com.example.reactor.model.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PersonRepo  extends ReactiveMongoRepository<Person, String> {



}
