package com.amigoscode.examples;

import com.amigoscode.beans.Car;
import com.amigoscode.beans.Person;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.hierarchical.OpenTest4JAwareThrowableCollector;

import static org.junit.jupiter.api.DynamicTest.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Filtering {

    @Test
    public void filter() throws Exception {
        
    	
    	System.out.println("Filter Cars");
    	
    	List<Car> cars = MockData.getCars();
        
        cars.stream()
        	.filter(c -> c.getColor().equals("Red"))
        	.filter(c -> c.getPrice() < 10000.00)
        	.collect(Collectors.toList())
        	.forEach(System.out::println);
        
       
    }

    @Test
    public void dropWhile() throws Exception {
        
        // Clasic Filter
        System.out.println("using filter");
        Stream.of(2, 4, 6, 8, 9, 10, 12)
                .filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // Using dropWhilw
        System.out.println("using dropWhile");
        Stream.of(2, 4, 6, 8, 9, 10, 12)
            .dropWhile(n -> n % 2 == 0)
            .forEach(n -> System.out.print(n + " "));
        
        System.out.println("\nFin");
        // Miestras sea par borra cuando llega al 9, no borra de ahi en adelante.        
    }

    @Test
    public void takeWhile() throws Exception {
        // using filter
        System.out.println("using filter");
        Stream.of(2, 4, 6, 8, 9, 10, 12)
            .filter(n -> n % 2 == 0)
            .forEach(n -> System.out.print(n + " "));

        System.out.println();
        System.out.println("using take while");
        
        Stream.of(2, 4, 6, 8, 9, 10, 12)
            .takeWhile(n -> n % 2 == 0)
            .forEach(n -> System.out.print(n + " "));
        
        System.out.println("\nFin");
        // Es al reves que el drop while, equivale a un do While cogiendo de la lista.
    }

    @Test
    public void findFirst() throws Exception {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        // using filter
        System.out.println("FIND FIRST");
        OptionalInt op = Arrays.stream(numbers).findFirst();
        op.ifPresent(System.out::print);
    }

    @Test
    public void findAny() throws Exception {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10};
        // using filter
        OptionalInt op = Arrays.stream(numbers).findAny();
        op.ifPresent(System.out::print);
    }
    
    @Test
    public void useSkipAndLimit() {
        
        
        System.out.println("**************************************************");
        String collect = Stream.iterate(0, n -> n+1)
                .skip(4)
                .limit(43)
                .collect(Collectors.toList())
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList())
                .stream().collect(Collectors.joining(", "));
        
        System.out.println(collect);
        
      
        System.out.println("**************************************************");        
    }

    @Test
    public void allMatch() throws Exception {
        int[] even = {2, 4, 6, 8, 10};
    }

    @Test
    public void anyMatch() throws Exception {
        int[] evenAndOneOdd = {2, 4, 6, 8, 10, 11};
    }

}



