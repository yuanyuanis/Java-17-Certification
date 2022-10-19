package com.amigoscode.examples;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.amigoscode.beans.Person;
import com.amigoscode.mockdata.MockData;


public class GettingStarted {

    @Test
    public void imperativeApproach() throws IOException {
        // 1. Find people aged less or equal 18
        // 2. Then change implementation to find first 10 people
        List<Person> people = MockData.getPeople();
        
        var first10YoungPleople = people.stream()
        	.filter(p-> p.getAge() <= 18).limit(10)
        	.collect(Collectors.toList());
        
        first10YoungPleople.forEach(System.out::println);
     
    }

    @Test
    public void declarativeApproachUsingStreams() throws Exception {
        // 1. Find people aged less or equal 18
        // 2. Then change implementation to find first 10 people
        List<Person> people = MockData.getPeople();
        
        var first10YoungPleople = people.stream()
        	.filter(p-> p.getAge() <= 18).limit(10)
        	.collect(Collectors.toList());
        
        first10YoungPleople.forEach(System.out::println);
     
    }
}
