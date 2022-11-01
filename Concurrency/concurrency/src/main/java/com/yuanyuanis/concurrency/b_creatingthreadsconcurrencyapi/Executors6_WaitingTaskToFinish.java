package com.yuanyuanis.concurrency.b_creatingthreadsconcurrencyapi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Executors6_WaitingTaskToFinish {

	public static void main(String[] args) throws InterruptedException {

		Runnable imprimeInventario = () -> System.out.println("imprimiendo inventario ZOO");
		Runnable imprimirRegistro = () -> {
			for (int i = 0; i <= 3; i++) {
				System.out.println("Imprimiendo Registro: " + i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		ExecutorService service = Executors.newSingleThreadExecutor();

		try {
			System.out.println("Comienza ...");

			service.execute(imprimeInventario);
			service.execute(imprimirRegistro);
			service.execute(imprimeInventario);

			System.out.println("Fin ...");

		} finally {
			service.shutdown();
		}
		
		// Espera hasta que la tarea termine, hasta se cumpla el tiempo o salte InterrumptedException
		service.awaitTermination(1, TimeUnit.SECONDS);
		
		
		// Comprobamos que las tareas hayan terminado
		if (service.isTerminated())
			System.out.println("Finished!");
		else
			System.out.println("At least one task is still running");
	}
}
