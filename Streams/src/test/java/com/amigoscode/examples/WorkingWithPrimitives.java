package com.amigoscode.examples;

import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WorkingWithPrimitives {
    
    public static void main(String ...args) {
        
        // Haz reduce de un Stream de Integer de tres elementos
        Stream<Integer> stream = Stream.of(1,2,4);
        System.out.println("Classic reduce stream.reduce(0, (x,y) -> x+y): " +stream.reduce(0, (x,y) -> x+y));
        
            /*
             * imprime
             * Classic reduce stream.reduce(0, (x,y) -> x+y): 7
             */
        
        Stream<Integer> stream2= Stream.of(1,2,4);
        System.out.println("stream.mapToInt(x -> x).sum(): "+stream2.mapToInt(x -> x).sum()); 
        
            /*
             * imprime
             * stream.mapToInt(x -> x).sum(): 7
             */
        
        
        IntStream intStream= IntStream.of(1,2,4);
        System.out.println(intStream.average());
            /**
             * Imprime un Optional
             * OptionalDouble[2.3333333333333335]
             */
        
        
        DoubleStream empty = DoubleStream.empty();
        empty.forEach(System.out::println);
        
            // No imprime nada
        
        
        DoubleStream threeElements = DoubleStream.of(3.14, 1.2, 1.3);
        OptionalDouble average = threeElements.average();
        average.ifPresent(System.out::println);
        
            // imprime la media
            // 1.8800000000000001
        
        DoubleStream.generate(Math::random)
                .limit(3)
                .forEachOrdered(System.out::println);
        
            /*
             *  0.07890654781186413
                0.28564363465842346
                0.6311403511266134
             */
        
        var fractions = DoubleStream
                .iterate(0.5, p -> p/2)
                .limit(3);
        fractions.forEach(System.out::println);
            
            /*
             *  0.5
                0.25
                0.125
             */
        
        IntStream range = IntStream.range(1, 6);
        range.forEach(p -> System.out.print(" "+p));
        System.out.println();
            //  1 2 3 4 5
        IntStream rangeClosed = IntStream.rangeClosed(1, 6);
        rangeClosed.forEach(p -> System.out.print(" "+p));
        System.out.println();
           //  1 2 3 4 5 6
        
        
        optionalConSterams();
        
    }

    private static void optionalConSterams() {
        
        var stream = IntStream.rangeClosed(1, 10);
        OptionalDouble optional = stream.average();
        
        optional.ifPresent(System.out::println); // 5.5
        System.out.println(optional.getAsDouble()); // 5.5
        System.out.println(optional.orElseGet(() -> Double.NaN)); //5.5
        
    }

}
