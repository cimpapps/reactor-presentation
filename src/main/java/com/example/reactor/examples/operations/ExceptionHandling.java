package com.example.reactor.examples.operations;

import com.example.reactor.examples.Utils;
import reactor.core.publisher.Flux;

public class ExceptionHandling {

    public static void main(String[] args) throws InterruptedException {
        Flux<String> upperCities = Utils.getFluxFromAtInterval(Utils.CITIES, 1)
                .map(city -> {
                    if ("Slatina".equals(city)) {
                        throw new RuntimeException("Nu primim olteni");
                    }
                    return city.toUpperCase();
                });

        //on Error este operatie terminala
//        upperCities.subscribe(
//                System.out::println,
//                System.err::println,
//                () -> System.out.println("Fluxul de date a ajuns la capat"));

//        upperCities.onErrorReturn("Nu mai suntem rasisti!!!!!!!!!!")
//                .subscribe(
//                System.out::println,
//                System.err::println,
//                () -> System.out.println("Fluxul de date a ajuns la capat"));

//        upperCities.onErrorResume(e-> Mono.just("Nu mai suntem rasisti!!!!!!!!!!"))
//                .subscribe(
//                        System.out::println,
//                        System.err::println,
//                        () -> System.out.println("Fluxul de date a ajuns la capat"));

//
//        upperCities.retry(2)
//                .subscribe(
//                System.out::println,
//                System.err::println,
//                () -> System.out.println("Fluxul de date a ajuns la capat"));


//                //primeste consumer
//                upperCities.doOnError(e-> System.out.println("S-a intamplat ceva naspa"))
//                .subscribe(
//                        System.out::println,
//                        System.err::println,
//                        () -> System.out.println("Fluxul de date a ajuns la capat"));


//        upperCities.doOnSubscribe(subscription -> {
//            //beginning operations ... open a IO connection...
//            System.out.println("Cineva se abona: ");
//        }).doFinally(signalType -> {
//            //finally
//            System.out.println("Finally close something dono");
//        }).subscribe(
//                System.out::println,
//                System.err::println,
//                () -> System.out.println("Fluxul de date a ajuns la capat"));


        Thread.currentThread().join();
    }

}
