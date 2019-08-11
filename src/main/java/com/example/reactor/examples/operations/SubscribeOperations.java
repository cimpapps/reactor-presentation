package com.example.reactor.examples.operations;

import com.example.reactor.examples.Utils;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

public class SubscribeOperations {

    public static void main(String[] args) throws InterruptedException {
        Flux<String> cities = Utils.getFluxFromAtInterval(Utils.CITIES, 1);

        cities.subscribe(city -> System.out.println("S1: " + city));


        cities.subscribe(city -> System.out.println("S2: " + city),
                err -> System.err.println(err.getMessage()));

        cities.subscribe(city -> System.out.println("S3: " + city),
                err -> System.err.println(err.getMessage()),
                () -> System.out.println(" s3: Fluxul de date s-a incheiat"));


//        Consumer<Subscription> subscriptionConsumer = subscription -> {
//            subscription.request(1);
//        };
//        cities.subscribe(city -> System.out.println("S4: " + city),
//                err -> System.err.println(err.getMessage()),
//                () -> System.out.println("Fluxul de date s-a incheiat"),
//                subscriptionConsumer);
//

        Thread.currentThread().join();
    }

}
