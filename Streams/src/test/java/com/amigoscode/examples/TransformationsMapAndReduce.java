package com.amigoscode.examples;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import com.amigoscode.beans.Car;
import com.amigoscode.beans.Person;
import com.amigoscode.beans.PersonDTO;
import com.amigoscode.mockdata.MockData;

public class TransformationsMapAndReduce {

    @Test
    void yourFirstTransformationWithMap() throws IOException {
        List<Person> people = MockData.getPeople();
        
        // Transformar  people en otro tipo de dato,
        // en este caso de Person a PersonDTO.
        List<PersonDTO> listPeopleDTO = people.stream()
        		.map(p -> {
        			return new PersonDTO(p.getId(), p.getFirstName(), p.getAge()); 
        		})
        		.collect(Collectors.toList());
        
        assertThat(people.size()).isEqualTo(listPeopleDTO.size());
        listPeopleDTO.forEach(System.out::println);
        
        
    }

    @Test
    void mapToDoubleAndFindAverageCarPrice() throws IOException {
        List<Car> cars = MockData.getCars();
        
        double avg = cars.stream()
        		.mapToDouble(Car::getPrice)
        		.average()
        		.orElse(0);
        
        System.out.println(" Average" +avg);
    }
    
    @Test
    void mapToOptionalString() throws IOException {
    	
    	Map<String, String> books = new HashMap<>();
	    	books.put(
	    	"978-0201633610", "Design patterns : elements of reusable object-oriented software");
	    	books.put(
	    	  "978-1617291999", "Java 8 in Action: Lambdas, Streams, and functional-style programming");
	    	books.put("978-0134685991", "Effective Java");
	    
	    
	    Optional<String> resultado = books.entrySet().stream()
	    		.filter(book -> book.getValue().contains("functional-style"))
	    		.map(Map.Entry::getKey)
	    		.findFirst();
	    
	    assertThat("functional-style".equals(resultado.get()));
	    
    	
    }

    /**
     * Usando la opcion terminal operation reduce cuando necesitamos acumulador
     * public T reduce(T identity, BinaryOperator<T> accumulator)
     */
    @Test
    public void reduce() {
        int[] integers = {1, 2, 3, 4, 99, 100, 121, 1302, 199};
        int sum = Arrays.stream(integers).reduce(0, Integer::sum);
        int sub = Arrays.stream(integers).reduce(0, (a, b) -> a - b);
        System.out.println(sum);
        System.out.println(sub);
    }
    
    
    /**
     * Usando la opcion terminal operation reduce cuando NO necesitamos acumulador, tres resultados posibles
     * 
     * public Optional<T> reduce(BinaryOperator<T> accumulator)
     */
    @Test
    public void reduceOptional() {
        
        Stream<Integer> empty = Stream.empty();
        Stream<Integer> element = Stream.of(15);
        Stream<Integer> elements = Stream.of(1,3,5,6);
        
        BinaryOperator<Integer> accumulator = (a,b) -> a*b;
        Optional<Integer> emptyResult = empty.reduce(accumulator);
        Optional<Integer> elementResult = element.reduce(accumulator);
        Optional<Integer> elementsResult = elements.reduce(accumulator);
        
        System.out.println("******Empty Stream*****");
        System.out.println("Is prensent?: " + emptyResult.isPresent() + " Is empty?: " + emptyResult.isEmpty());
        
        System.out.println("******Element*****");
        elementResult.ifPresent(System.out::print);
        
        System.out.println("******Multiple Elements*****");
        elementsResult.ifPresent(System.out::println);
        
    }
    
    /**
     *  
        public <U> U reduce(U identity,
        BiFunction<U,? super T,U> accumulator,
        BinaryOperator<U> combiner)
     */
    @Test
    public void reduceMultipleTypes() {
        Stream<String> stream = Stream.of("w","o","l", "f!");
        int lenght = stream.reduce(0, (a, b) -> a+b.length(), (i,z) ->i+z);
        System.out.println(lenght);
        
    }
    
}

