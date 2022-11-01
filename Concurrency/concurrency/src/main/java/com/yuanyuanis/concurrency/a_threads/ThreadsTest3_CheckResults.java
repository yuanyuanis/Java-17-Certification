package com.yuanyuanis.concurrency.a_threads;

public class ThreadsTest3_CheckResults {
	
	private static int counter = 0;

	public static void main(String[] args) {
		
		new Thread(() -> {
			for (int i = 0; i < 1_000_00000; i++)
				counter++;
		}).start();
		
		while (counter < 1_000_000) {
			System.out.println("Not reached yet");
			
			// El problema es que esta condicion se repite demasiado. Lo que hace un consumo muy intenso de CPU
			// Lo que sin duda es un error de diseño.
			
		}
		System.out.println("Reached: " + counter);
	}
}
