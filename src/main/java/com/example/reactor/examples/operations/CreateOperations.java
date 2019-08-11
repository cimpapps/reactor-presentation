package com.example.reactor.examples.operations;

import com.example.reactor.examples.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;

public class CreateOperations {

    public static void main(String[] args) {
        Flux.just(1, 2, 3);
        Integer[] ints = {1, 2, 3, 4, 5};
        Flux.fromArray(ints);
        Flux.fromIterable(Arrays.asList(1, 2, 3));
        Flux.fromStream(Stream.of(1, 2, 3));
        Flux.from(Flux.interval(Duration.ofSeconds(1)));

        Flux<Object> programaticallyFlux = Flux.generate(
                () -> System.currentTimeMillis(),
                (state, sink) -> {
                    Utils.pause(500);
                    sink.next(state);
                    if (state % 5 == 0) {
                        sink.complete();
                    }
                    return System.currentTimeMillis();
                }
        );

        programaticallyFlux.subscribe(System.out::println);

    }
}
