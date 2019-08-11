package com.example.reactor.examples;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;

import static com.example.reactor.examples.Utils.CITIES;
import static com.example.reactor.examples.Utils.pause;

public class FromCustomToFluxPublisher {

    public static void main(String[] args) throws InterruptedException {

        Flux<String> cityPublisher = Flux.fromIterable(CITIES)
                .zipWith(Flux.interval(Duration.ofSeconds(1)))
                .map(Tuple2::getT1);

        System.out.println("Nothing happens until someone subscribes");

        pause(3000);

        cityPublisher.subscribe(
                System.out::println,
                System.err::println,
                () -> System.out.println("Fluxul de date s-a terminat")
        );

        System.out.println("The operations are non blocking because are executed async"); //OR YAASSS!


//        //Programatically created flux
//        Flux<String> flux = Flux.generate(
//                () -> 0,
//                (state, sink) -> {
//                    sink.next("3 x " + state + " = " + 3*state);
//                    if (state == 10) sink.complete();
//                    return state + 1;
//                });
//
//        flux.subscribe(System.out::println);

        Thread.currentThread().join();
    }
}