package com.amigoscode.examples;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class MinMax {

    @Test
    public void minNumber() {
        List<Integer> numbers = List.of(1, 2, 3, 100, 23, 93, 99);
        Optional<Integer> min = numbers.stream().min((i1, i2) -> i1.compareTo(i2));
        
        min.ifPresent(System.out::println); // 1
        
    }

    @Test
    public void maxNumber() {
        List<Integer> numbers = List.of(1, 2, 3, 100, 23, 93, 99);
        Optional<Integer> max = numbers.stream().max((i1, i2) -> i1.compareTo(i2));    

        
        max.ifPresent(System.out::println); // 100
    }
    
    @Test
    public void sortedText() {
        
        Stream<String> s = Stream.of("monkey", "ape", "bonobo");
        Optional<String> min = s.min((s1, s2) -> s1.length()-s2.length());
        
        min.ifPresent(System.out::println); // ape
    }
    
    
}
