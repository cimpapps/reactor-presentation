package com.example.reactor.examples;

import reactor.core.publisher.Flux;

import java.util.Comparator;

import static com.example.reactor.examples.Utils.*;

public class CombineOperations {


    public static void main(String[] args) throws InterruptedException {
        CombineOperations op = new CombineOperations();
//        op.combineLatest().subscribe(System.out::println);
//        op.merge().subscribe(System.out::println);
//        op.mergeOrdered().subscribe(System.out::println);
//        op.zip().subscribe(System.out::println);
        op.concat().subscribe(System.out::println);


        Thread.currentThread().join();
    }


    private Flux<String> combineLatest() {
        return Flux.combineLatest(getIterableAtInterval(NUMBERS, 1),
                getIterableAtInterval(BIGGER_NUMBERS,2),
                (nr, city) ->"Combine latest: " + nr + "-" + city);
    }


    private Flux<Integer> merge() {
        return getIterableAtInterval(NUMBERS, 1)
                .mergeWith(getIterableAtInterval(BIGGER_NUMBERS, 2));
    }


    private Flux<Integer> mergeOrdered() {
        return getIterableAtInterval(BIGGER_NUMBERS, 1)
                .mergeOrderedWith(getIterableAtInterval(NUMBERS, 1), Comparator.naturalOrder());
    }

    private Flux<String> zip() {
        return getIterableAtInterval(NUMBERS, 1)
                .zipWith(getIterableAtInterval(BIGGER_NUMBERS, 2))
                .map(tuple2 -> tuple2.getT1() + "-" + tuple2.getT2());
    }

    protected Flux<Integer> concat() {
        return getIterableAtInterval(NUMBERS, 2)
                .concatWith(getIterableAtInterval(BIGGER_NUMBERS, 1));

    }
}
