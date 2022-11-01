package com.yuanyuanis.concurrency.a_threads;
/**
 * 
 * @author Juan Ibañez
 *
 */
public class ThreadsTest1 {

	public static void main(String... args) {
		Runnable tarea1 = () -> System.out.println("Se esta ejecutando el Thread: " + Thread.currentThread().getName());
		Runnable tarea2 = () -> {
			for (int i = 0; i <= 3; i++) {
				System.out
						.println("Se esta ejecutando el Thread: " + Thread.currentThread().getName() + " vuelta: " + i);
				;
			}
		};
		
		Thread hilo1 = new Thread(tarea1,"Hilo1");
		Thread hilo2 = new Thread(tarea2, "Hilo2");
		
		// Thread hilo1 = new Thread(new Task1(),"Hilo1");
		// Thread hilo2 = new Thread(new Task2(), "Hilo2");
		
		hilo1.start();
		hilo2.start();
	}
	
	
	/*
	 * Creando Runnable con codigo  Java 1.7 o menor
	 */
	static class Task1 implements Runnable{

		@Override
		public void run() {
			System.out.println("Se esta ejecutando el Thread: " + Thread.currentThread().getName());
			
		}
	}
	static class Task2 implements Runnable{

		@Override
		public void run() {
			for (int i = 0; i <= 3; i++) {
				System.out
						.println("Se esta ejecutando el Thread: " + Thread.currentThread().getName() + " vuelta: " + i);
				;
			}
		}
	}
}
