package com.yuanyuanis.concurrency.ocp.e_paralellStream;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class B_ParallelStreams {
	
	private static int hacerTrabajo(final int input) {
		
		try {
			Thread.currentThread();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return input;
	}

	public static void main(String[] args) {
		
		// Formas de crear un ParallelStream
		Collection<Integer> coleccion = List.of(1, 2, 3);
		Stream<Integer> stream1  = coleccion.stream().parallel();
		Stream<Integer> stream2  = coleccion.parallelStream();
		
		Instant start = Instant.now();
		
		List.of(1,2,3,4,5,6,7,8)
			.parallelStream()
			.map(x -> hacerTrabajo(x))
			.forEach(s -> System.out.print(s+" "));
		
		Instant end = Instant.now();
		System.out.println("\nTiempo transcurrido: " + Duration.between(start, end));
		// Con parallel Stream el orden no esta garantizado
	}

}
