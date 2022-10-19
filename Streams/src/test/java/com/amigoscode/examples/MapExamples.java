package com.amigoscode.examples;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import com.amigoscode.beans.Book;

public class MapExamples {
    
    @Test
    public void mapFirst() {
        
        List<Book> lista = List.of(
                new Book("dddRa", 1999, "123654"),
                new Book("ssa historia interminable", 1984, "1236asd54"),
                new Book("aaaRatatui", 1990, "123654asd"));
        
        
        List<String> listaTitulos = lista.stream()
            .map(Book::getName)
            .collect(Collectors.toList());
        
        listaTitulos.stream()
            .map(String::toUpperCase)
            .sorted()
            .forEach(System.out::println);
                        // AAARATATUI
                        // DDDRA
                        // SSA HISTORIA INTERMINABLE
     
        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
            s.map(String::length)
           .forEach(System.out::print); // 676        
    }
    

}
