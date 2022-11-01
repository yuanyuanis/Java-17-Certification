package com.yuanyuanis.concurrency.a_threads;

public class ThreadsTest4_CheckResultsAndSleep {
	private static int counter = 0;

	public static void main(String[] args) {
		
		new Thread(() -> {
			for (int i = 0; i < 1_000_000; i++)
				counter++;
		}).start();
		
		while (counter < 1_000_000) {
			System.out.println("Not reached yet");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Interrumped");
			}
			/**
			 * Si bien un segundo puede parecer una cantidad pequeña, ahora liberamos a la CPU para que realice otro trabajo 
			 * en lugar de verificar la variable del contador infinitamente dentro de un ciclo. 
			 * Observe que el subproceso main() alterna entre TIMED_WAITING y RUNNABLE cuando se ingresa y se sale de sleep(), respectivamente.
			 * ¿Cuántas veces se ejecuta el ciclo while() en esta clase revisada? 
			 * ¡Aún desconocido! Si bien el sondeo evita que la CPU se vea abrumada con un bucle potencialmente infinito, 
			 * no garantiza cuándo terminará el bucle. 
			 * Por ejemplo, el subproceso separado podría estar perdiendo tiempo de CPU para un proceso de mayor prioridad, 
			 * lo que resultaría en múltiples ejecuciones del bucle while() antes de que finalice.
			 */
		}
		System.out.println("Reached: " + counter);
	}
}
