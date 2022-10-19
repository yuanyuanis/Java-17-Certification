package com.amigoscode.examples;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorkingWithStreams {

    @Test
    void steams() {
        List<String> names = List.of("Amigoscode", "Alex", "Zara");
        Stream<String> stream = names.stream();
        
        Stream<String> namesStream = Stream.of("Amigoscode", "Alex", "Zara");
        
        Long count = stream.filter(c -> c.contains("A"))        	
            .limit(2)
        	.count();
        
        assertThat(count.equals(2));
        
        String[] arraysNames = {};
        
        Arrays.stream(arraysNames);
        
        
        
    }
    
    @Test
    void finiteStreams() {
        
        // crear un array vacio
        Stream<String> empty = Stream.empty();
        Stream<Integer> unElemento = Stream.of(1);
        Stream<Integer> masElementos = Stream.of(1, 2, 3);
        
        System.out.println(empty);
        
        // Esto imprime algo así
        // java.util.stream.ReferencePipeline$Head@233fe9b6
        
        // Otra forma es generando cosas.
        Stream<Integer> imparesDebajo100 = Stream.iterate(
                1,                // seed
                n -> n < 100,     // Predicate to specify when done
                n -> n + 2);      // UnaryOperator to get next value
        
        var lista = imparesDebajo100.collect(Collectors.toList());
        lista.forEach(p -> System.out.print(""+p+","));
        

    }
}
