package com.yuanyuanis.concurrency.b_creatingthreadsconcurrencyapi;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Executors7_ScheduledExecutorRunnable {

	public static void main(String[] args) {
		
		ScheduledExecutorService schedulerService = Executors.newScheduledThreadPool(1);
		
		Runnable tarea2 = () -> System.out.println("Corriendo Tarea2 ... ");
		
		tarea1();
		
		// Programada para hacer correr esta tarea en 5 segundos.
		schedulerService.schedule(tarea2, 5, TimeUnit.SECONDS);
		
		tarea3();
		
		schedulerService.shutdown();

	}
	
	public static void tarea1() {
		System.out.println("Corriendo Tarea1 ...");
	}
	
	public static void tarea3() {
		System.out.println("Corriendo Tarea3 ...");
	}

}
