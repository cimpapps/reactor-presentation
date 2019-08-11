package com.example.reactor.examples;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public interface Utils {

    List<Integer> NUMBERS = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    List<Integer> REVERSED_NUMBERS = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1);
    List<Integer> BIGGER_NUMBERS = Arrays.asList(101, 102, 103, 104, 105, 106, 107, 108, 109);

    List<String> CITIES = Arrays.asList("Arad", "Timisoara", "Cluj", "Slatina", null, "Curtea de Arges", "Vaslui Rullz",
            "Campina", "Barlad", "Bucuresti");


    List<String> NAMES = Arrays.asList("Cimpoeru", "Chiriluta", "Pestrescu", "Pistol");


    public static void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static <V> Flux<V> getIterableAtInterval(Iterable<V> iterable, int i) {
        return Flux.fromIterable(iterable)
                .zipWith(Flux.interval(Duration.ofSeconds(i)))
                .map(Tuple2::getT1);
    }


    public static void main(String[] args) throws InterruptedException {
        getIterableAtInterval(CITIES, 1).subscribe(System.out::println, e -> System.out.println(e), () -> System.out.println("s-a terminat"));
        Thread.currentThread().join();
    }
}
