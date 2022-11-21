package com.yuanyuanis.concurrency.ocp.f_concurrentCollections;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TrabajarConConleccionesConcurrentes {

	public static void main(String[] args) {
		
	    List<Integer> numerosFavoritos = new CopyOnWriteArrayList<>(List.of(4, 3, 42));
	    for (var n : numerosFavoritos) {
	        System.out.print(n + " "); // 4 3 42
	        numerosFavoritos.add(n + 1);
	    }

	    System.out.println();
	    System.out.println("Tamaño: " + numerosFavoritos.size()); // Size: 6
	    System.out.println(numerosFavoritos); // [4, 3, 42, 5, 4, 43]

	    
	}

}
