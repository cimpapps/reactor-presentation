package com.example.reactor.examples;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import static com.example.reactor.examples.Utils.NUMBERS;
import static com.example.reactor.examples.Utils.pause;

public class ConcurrencyWithReactor {

    public static void main(String[] args) throws InterruptedException {
        Flux<Integer> nrFlux = Flux.fromIterable(NUMBERS);

        Flux<Integer> nrFlux100 = nrFlux.map(nr -> {
            pause(1000);
            System.out.println(Thread.currentThread() + " execut " + nr);
            return nr * 100;
        });

        Flux<Integer> elasticFlux = nrFlux100.subscribeOn(Schedulers.elastic());

        Flux<Integer> parallelFlux = nrFlux100.subscribeOn(Schedulers.parallel());

        Flux<Integer> newParallel = nrFlux100.subscribeOn(Schedulers.newParallel("my-parallel-pool", 23));

        Flux<Integer> fluxPublishAfter = nrFlux100.publishOn(Schedulers.elastic());

        Flux<Integer> mapAgain = fluxPublishAfter.map(nr100 -> {
            pause(6000);
            System.out.println(Thread.currentThread() + "-" + nr100);
            return nr100;
        });

//        subscribe(mapAgain, 2000);


        Flux.fromIterable(NUMBERS)
                .flatMap(nr -> {
                    return Mono.just(nr).map(nr1 -> {
                        System.out.println(Thread.currentThread() + "  " + nr1);
                        pause(1000);
                        return nr1 * 3;
                    }).subscribeOn(Schedulers.elastic());
                }).subscribe(System.out::println);

        Thread.currentThread().join();

    }

    static <T> void subscribe(Flux<T> flux, int nrSubscriptions) {
        for (int i = 0; i < nrSubscriptions; i++) {
            flux.subscribe();
        }
    }

}
