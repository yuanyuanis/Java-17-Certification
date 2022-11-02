package com.yuanyuanis.concurrency.f_concurrentCollections;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrencyCollectionProblems {

	public static void main(String[] args) {

		var mapNumAnimales = new HashMap<String, Integer>();
		mapNumAnimales.put("Perros", 3);
		mapNumAnimales.put("Gatos", 2);

		try {
			for (Map.Entry<String, Integer> entry : mapNumAnimales.entrySet()) 
				mapNumAnimales.remove("Perros"); // java.util.ConcurrentModificationException
			
		} catch (ConcurrentModificationException e) {
			System.err.println("Error al remover: "+e);
		}
		
		
		var mapNumAnimalesConcurrent = new ConcurrentHashMap<>(mapNumAnimales);
		for (Map.Entry<String, Integer> entry : mapNumAnimales.entrySet()) 
			mapNumAnimales.remove("Perros");
		
		System.out.println(mapNumAnimalesConcurrent); // {Gatos=2}

	}

}
