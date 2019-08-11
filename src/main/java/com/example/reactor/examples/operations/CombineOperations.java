package com.example.reactor.examples.operations;

import reactor.core.publisher.Flux;

import java.util.Comparator;

import static com.example.reactor.examples.Utils.*;

public class CombineOperations {


    public static void main(String[] args) throws InterruptedException {
        CombineOperations op = new CombineOperations();
//        op.combineLatest().subscribe(System.out::println);
        op.withLastestFrom().subscribe(System.out::println);
//        op.merge().subscribe(System.out::println);
//        op.mergeOrdered().subscribe(System.out::println);
//        op.zip().subscribe(System.out::println);
//        op.concat().subscribe(System.out::println);


        Thread.currentThread().join();
    }


    private Flux<String> combineLatest() {
        return Flux.combineLatest(getFluxFromAtInterval(NUMBERS, 1),
                getFluxFromAtInterval(BIGGER_NUMBERS, 2),
                (nr, city) -> "Combine latest: " + nr + "-" + city);
    }


    private Flux<String> withLastestFrom() {
        return getFluxFromAtInterval(NUMBERS, 1)
                .withLatestFrom(getFluxFromAtInterval(BIGGER_NUMBERS, 2), (n1, n2) -> "wLatest:" + n1 + "-" + n2);
    }

    private Flux<Integer> merge() {
        return getFluxFromAtInterval(NUMBERS, 1)
                .mergeWith(getFluxFromAtInterval(BIGGER_NUMBERS, 2));
    }


    private Flux<Integer> mergeOrdered() {
        return getFluxFromAtInterval(BIGGER_NUMBERS, 1)
                .mergeOrderedWith(getFluxFromAtInterval(NUMBERS, 1), Comparator.naturalOrder());
    }

    private Flux<String> zip() {
        return getFluxFromAtInterval(NUMBERS, 1)
                .zipWith(getFluxFromAtInterval(BIGGER_NUMBERS, 2))
                .map(tuple2 -> tuple2.getT1() + "-" + tuple2.getT2());
    }

    protected Flux<Integer> concat() {
        return getFluxFromAtInterval(NUMBERS, 2)
                .concatWith(getFluxFromAtInterval(BIGGER_NUMBERS, 1));

    }
}
