package com.amigoscode.examples;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class Collecting {

    private Stream<String> stream = Stream.of("w", "o", "l", "f");
    List<String> givenList = List.of("a", "bb", "ccc", "dd");

    /**
     * Using API:
     * public <R> R collect(Supplier<R> supplier,
     * BiConsumer<R, ? super T> accumulator,
     * BiConsumer<R, R> combiner)
     * 
     */
    @Test
    public void streamToStringBuilder() {

        Stream<String> stream = Stream.of("w", "o", "l", "f");
        StringBuilder builder = stream.collect(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append);

        assertThat(builder.toString()).isEqualToIgnoringWhitespace("wolf");
    }

    /**
     * Using API:
     * public <R> R collect(Supplier<R> supplier,
     * BiConsumer<R, ? super T> accumulator,
     * BiConsumer<R, R> combiner)
     * 
     */
    @Test
    public void streamToTreeSet() {
        TreeSet<String> threeSet = stream.collect(
                TreeSet::new,
                TreeSet::add,
                TreeSet::addAll);

        System.out.println(threeSet); // [f, l, o, w]
    }

    /**
     * Using API:
     * public <R> R collect(Supplier<R> supplier,
     * BiConsumer<R, ? super T> accumulator,
     * BiConsumer<R, R> combiner)
     * 
     */

    @Test
    public void streamToTreeSetUsingCollectors() {

        TreeSet<String> result = stream.collect(
                Collectors.toCollection(TreeSet::new));
        System.out.println(result); // [f, l, o, w]

    }

    @Test
    public void streamToListUsingCollectors() {

        var list = List.of("aa", "bb", "zz", "bb", "asd", "1");

        var conjunto = list.stream().collect(Collectors.toList());

        assertThat(conjunto).isExactlyInstanceOf(ArrayList.class).hasSize(6);

        // ToUnmodificableSet and ToUnModificableList
        List<String> result = List.of("aa", "bb").stream()
                .collect(Collectors.toUnmodifiableList());

        assertThatThrownBy(() -> result.add("test")).isInstanceOf(UnsupportedOperationException.class);

    }

    @Test
    public void streamToSetUsingCollectors() {

        var list = List.of("aa", "bb", "zz", "bb", "asd", "1");
        var conjunto = list.stream().collect(Collectors.toSet());

        assertThat(conjunto).isExactlyInstanceOf(HashSet.class).hasSize(5);

        // ToUnmodificableSet and ToUnModificableList
        Set<String> result = List.of("aa", "bb").stream()
                .collect(Collectors.toUnmodifiableSet());

        assertThatThrownBy(() -> result.add("test")).isInstanceOf(UnsupportedOperationException.class);

    }

    @Test
    public void collectorsToList() {
        var list = List.of("aa", "bb", "zz", "bb", "asd", "1");

        var lista = list.stream().collect(Collectors.toList());

        assertThat(lista).isExactlyInstanceOf(ArrayList.class).hasSize(6);

        // ToUnmodificableSet and ToUnModificableList
        List<String> result = List.of("aa", "bb").stream()
                .collect(Collectors.toUnmodifiableList());

        assertThatThrownBy(() -> result.add("test")).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void collectorsToMap() {

        givenList.stream()
                .collect(Collectors.toMap(Function.identity(), p -> p.length()));

        List<String> listWithDuplicates = Arrays.asList("a", "bb", "c", "d", "bb");

        assertThatThrownBy(() -> listWithDuplicates.stream()
                .collect(Collectors.toMap(Function.identity(), String::length)))
                .isInstanceOf(IllegalStateException.class);

    }

    @Test
    public void collectorsJoining() {
        String result = givenList.stream().collect(Collectors.joining());
        System.out.println("!collectorsJoining: " + result);

        String result2 = givenList.stream().collect(Collectors.joining(" "));
        System.out.println("!collectorsJoining: " + result2);

        String result3 = givenList.stream()
                .collect(Collectors.joining(" ", "PRE-", "-POST"));
        System.out.println("!collectorsJoining: " + result3);

    }

}
