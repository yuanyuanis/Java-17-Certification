package com.yuanyuanis.concurrency.ocp.b_creatingthreadsconcurrencyapi;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Executors8_ScheduledExecutorCallable {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ScheduledExecutorService schedulerService = Executors.newScheduledThreadPool(1);
		
		Callable<String> tarea2 = () -> {
			System.out.println("Corriendo Tarea2 ... ");
			return "[UnMonoPrecioso]";
		};
		
		tarea1();
		
		// Programada para hacer correr esta tarea en 5 segundos.
		ScheduledFuture<String> future  =  schedulerService.schedule(tarea2, 5, TimeUnit.SECONDS);
		
		// Con la invocacion de get, el hilo principal se queda esperando a que acave la tarea
		String resultado = future.get();
		System.out.println("El resultado de la tarea2 es: "+resultado);
		
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
