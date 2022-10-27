package com.amigoscode.examples;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicCollectors {

    public static void main(String[] args) {

        // joining
        var ohMy = Stream.of("lions", "tigers", "bears");
        String result = ohMy.collect(Collectors.joining(", "));
        System.out.println(result); // lions, tigers, bears

        // Average
        var simpleSteram = Stream.of("test1", "palabra67", "palabramuylarga");
        Double dresult = simpleSteram.collect(Collectors.averagingInt(String::length));
        System.out.println(dresult);// 9.666666666666666

        // Count
        var stream = Stream.of(2, 2, 4, 4, 4, 4, 4, 65, 67, 7, 3455, 234, 234, 234, 234, 23, 234);
        Long nElements = stream.collect(Collectors.counting());
        System.out.println(nElements);// 17

        // toCollection
        var names = Stream.of("Juan", "Laura", "Arturo", "Ita");

        TreeSet<String> simpleTree = names
                .filter((p) -> p.contains("t"))
                .collect(Collectors.toCollection(TreeSet::new));

        simpleTree.forEach(System.out::print);

        // Collect to Maps
        var cars = Stream.of("Seat", "Renault", "Audi", "Fiat", "Ferrari");

        Map<String, Integer> map = cars.collect(Collectors.toMap(s -> s, String::length));
        System.out.println(map);

        
        
        // Una pregunta con truco, ahora vamos a hacer el reverso de la prueba anterior, de forma qie el Map se contenga de 
        // Integer  como clave y String valor.
        try {
            var test = Stream.of("lions", "tigers", "bears");
            Map<Integer, String> someMap = test.collect(Collectors.toMap(
                    String::length,
                    k -> k)); // BAD
           
            
        } catch (Exception e) {
            System.out.println(e.getCause());
            /*
             * Running this gives an exception similar to the following:
             * 
             * Exception in thread "main"
             * java.lang.IllegalStateException: Duplicate key 5
             */
        }
        
        var testt = Stream.of("lions", "tigers", "bears");
        TreeMap<Integer, String> mapas = testt.collect(Collectors.toMap(
            String::length,
            k -> k,
            (s1, s2) -> s1 + "," + s2,
            TreeMap::new));
        System.out.println(map); //         // {5=lions,bears, 6=tigers}
        System.out.println(map.getClass()); // class java.util.TreeMap
        
        // Prueba de goupingBy
        var prueba = List.of("Perro", "Gato", "Oso", "Cisne", "Pato", "Liebre", "Conejo"); 
        
        Map<Integer, List<String>> cosas = prueba.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(cosas); // {3=[Oso], 4=[Gato, Pato], 5=[Perro, Cisne], 6=[Liebre, Conejo]}
        
        // Prueba de goupingBy con Collectors para pasar a set
        var conjunto = List.of("Perro", "Gato", "Oso", "Cisne", "Pato", "Liebre", "Conejo");
        
        Map<Integer, Set<String>> grouping = conjunto.stream().collect
                (Collectors.groupingBy(String::length, 
                        TreeMap::new, 
                        Collectors.toSet()));
        
        System.out.println(grouping);
        
        // Prueba de Partitioning 
        var listilla = List.of("Perro", "Gato", "Oso", "Cisne", "Pato", "Liebre", "Conejo");
        Map<Boolean, List<String>> kkk = listilla.stream().collect(Collectors.partitioningBy((s) -> s.length() <= 5));
        System.out.println(kkk); //{false=[], true=[Perro, Gato, Oso, Cisne, Pato, Liebre, Conejo]}

;        

    }

}
