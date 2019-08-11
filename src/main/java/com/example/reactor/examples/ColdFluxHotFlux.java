package com.example.reactor.examples;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import static com.example.reactor.examples.Utils.*;

public class ColdFluxHotFlux {

    public static void main(String[] args) throws InterruptedException {

//        //Cold Flux
        Flux<String> coldCities = getFluxFromAtInterval(CITIES, 1);
        coldCities.subscribe(city -> System.out.println("S1: " + city));

        pause(3000);

        coldCities.subscribe(city -> System.out.println("  S2: " + city));


        pause(1000);


        //hot flux
        Flux<String> hotCities = getFluxFromAtInterval(CITIES, 1).publish() ;

        pause(2500);
        //if you subscribe before connect you will get all the data also with the hotflux
        hotCities.subscribe(city -> System.out.println("Before connect: " + city));

        //after the connect the hot flux start to emit data and will not keep the old data
        ((ConnectableFlux<String>) hotCities).connect();
        pause(1500);

        //this subscriber looses the first city because the hot flux started to emit 1.5 seconds ago
        hotCities.subscribe(city -> System.out.println("S3: " + city));

        pause(1000);

        hotCities.subscribe(city -> System.out.println("  S4: " + city));

        Thread.currentThread().join();

    }
}
