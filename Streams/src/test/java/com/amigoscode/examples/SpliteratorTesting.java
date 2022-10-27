package com.amigoscode.examples;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;

public class SpliteratorTesting {

    public static void main(String[] args) {

        var lista = List.of("bird-", "bunny-", "cat-", "dog-", "fish-", "lamb-", "mouse-");
        Spliterator<String> bolsaDeComidaOriginal = lista.spliterator();

        Spliterator<String> primeraBolsa = bolsaDeComidaOriginal.trySplit();
        primeraBolsa.forEachRemaining(System.out::print);

        var originalBag = Stream.iterate(1, n -> ++n)
                .spliterator();

        Spliterator<Integer> newBag = originalBag.trySplit();

        newBag.tryAdvance(System.out::print); // 1
        newBag.tryAdvance(System.out::print); // 2
        newBag.tryAdvance(System.out::print); // 3

    }

}
