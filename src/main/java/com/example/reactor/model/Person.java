package com.example.reactor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "persons")
@Data
@AllArgsConstructor
public class Person {

    private String id;
    private String name;
    private String profession;


}
