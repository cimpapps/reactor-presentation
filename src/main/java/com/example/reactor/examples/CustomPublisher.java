package com.example.reactor.examples;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import static com.example.reactor.examples.Utils.CITIES;
import static com.example.reactor.examples.Utils.pause;

public class CustomPublisher {

    public static void main(String[] args) throws InterruptedException {

        CityPublisher cityPublisher = new CityPublisher();

        System.out.println("Nothing happens until someone subscribes");

        pause(3000);

        cityPublisher.subscribe(new CitySubscriber());
        System.out.println("The operations are non blocking because are executed async"); //OR NOT

        Thread.currentThread().join();
    }
}

//TODO show warning
class CityPublisher implements Publisher<String> {
    @Override
    public void subscribe(Subscriber<? super String> subscriber) {
        CITIES.forEach(city -> {
            pause(500);
            if (city == null) {
                subscriber.onError(new RuntimeException("Nu se egzicsta acest oras"));
                return;
            }
            subscriber.onNext(city);

        });
        subscriber.onComplete();
    }

}
//TODO show warning
class CitySubscriber implements Subscriber<String> {
    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(1);
        System.out.println("Subscription started");
    }

    @Override
    public void onNext(String s) {
        System.out.println("Shitty: " + s);
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.println(throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("S-a terminat flowul de date");
    }
}

