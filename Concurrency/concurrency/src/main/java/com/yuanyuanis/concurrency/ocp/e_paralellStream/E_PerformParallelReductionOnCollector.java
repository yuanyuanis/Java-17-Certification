package com.yuanyuanis.concurrency.ocp.e_paralellStream;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class E_PerformParallelReductionOnCollector {
	public static void main(String[] args) {

		// Requerimientos para hacer Parallel Reduction with collect()
		
		// 1) El stream es parallel.
		// 2) El parametro de la operación `collect()` tiene la caracteristica `Characteristics.CONCURRENT`
		// 3) El stream es `unordered` o el collector tiene la caracteristica `Characteristics.UNORDERED`.
		
		// EJEMPLO1 -> 
		List.of(2, 3, 4).parallelStream().collect(Collectors.toSet());
		// No hace parallel reduction ya que Set es UNORDERED pero no CONCURRENT
		
		// EJEMPLO2 -> 
		Stream<String> ouch = Stream.of("Perro", "Gato", "Leon");
		ConcurrentMap<Integer, Object> map = ouch.collect(Collectors.toConcurrentMap(String::length, k -> k, (s1, s2) -> s1+","+s2));
		System.out.println(map); // {4=Gato,Leon, 5=Perro}
		System.out.println(map.getClass());  //class java.util.concurrent.ConcurrentHashMap
		
		// EJEMPLO3 --> groupingByConcurrent
		Stream<String> nombres = Stream.of("Juan", "Laura", "Pablo", "Jesus", "Barbara", "Gillermo", "Javier", "Celia", "Maria", "Rosa", "Ita");
		ConcurrentMap<Integer, List<String>> agrupados = nombres.collect(
				Collectors.groupingByConcurrent(String::length));
		System.out.println(agrupados);
		
		

		

	}

}
