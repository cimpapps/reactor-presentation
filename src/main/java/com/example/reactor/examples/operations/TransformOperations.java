package com.example.reactor.examples.operations;

import com.example.reactor.examples.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class TransformOperations {

    public static void main(String[] args) throws InterruptedException {
        Flux<Integer> interval = Utils.getFluxFromAtInterval(Utils.NUMBERS, 2);

        Flux<Integer> mapper = interval.map(tick -> tick * 2);

        Flux<Integer> filterFlux = interval.filter(tick -> tick % 2 == 0);

        Flux<Integer> flatter = interval.flatMap(tick -> Mono.just(new Random(tick).nextInt()));

        Mono<List<Integer>> listMono = interval.collectList();

        Mono<Map<String, Integer>> mapMono = interval.collectMap(nr -> UUID.randomUUID().toString());

        Mono<AtomicInteger> collect = interval.collect(
                () -> new AtomicInteger(100),
                (n1, n2) -> {
                    System.out.println(n1.addAndGet(n2));;
                });
        collect.subscribe(result -> System.out.println("Rezultatul " + result));

        Thread.currentThread().join();
    }


    public static String someServiceOperation(long nr) {
        Utils.pause(500);
        return "#" + nr;
    }


}
