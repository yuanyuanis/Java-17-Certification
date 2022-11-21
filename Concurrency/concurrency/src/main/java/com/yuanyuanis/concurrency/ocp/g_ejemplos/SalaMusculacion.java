package com.yuanyuanis.concurrency.ocp.g_ejemplos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

public class SalaMusculacion {

	private Logger logger = Logger.getLogger(SalaMusculacion.class.getName());
	private final Semaphore puestos = new Semaphore(4);

	public void entrenar() {
		
		final String usuario = Thread.currentThread().getName();
		
		logger.info(String.format("El usuario %s desea entrenarse", usuario));
		imprimirPuestosDisponibles();
		try {
			puestos.acquire();
			
			// Ahora se puede entrenar
            System.out.println("Inicio del entrenamiento de "+usuario);
            Thread.sleep(1000);
            System.out.println("Fin del entrenamiento para "+usuario);
			
		} catch (InterruptedException e) {
			logger.severe("Thread "+ usuario + " ha sido interrumpido");		
		}finally {
			puestos.release();
			logger.info("liberado un puesto");
			logger.info(String.format("Hay disponibles %d puestos", puestos.availablePermits()));
		}
			
	}

	public void imprimirPuestosDisponibles() {
		logger.info(String.format("Hay disponibles %d puestos", puestos.availablePermits()));
	}
	
	
	
	public static void main(String ...args) {
		var salaMusculacion = new SalaMusculacion();
		
		int numProcesadores = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = Executors.newFixedThreadPool(numProcesadores);
		
		Runnable runnable = () -> salaMusculacion.entrenar();
		
		for (int i = 0; i < numProcesadores; i++) {
			executor.submit(runnable);
		}
		executor.shutdown();
	}
	
	// Descomentar para trabajar con Threads
	/*public static void main(String ...args) {
	var salaMusculacion = new SalaMusculacion();
	
	ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	
	Runnable runnable = () -> salaMusculacion.entrenar();
	
	for (int i = 0; i < 6; i++) {
		
		final String nombreDeportisa = "Deportista n."+(i+1);
		
		new Thread(runnable, nombreDeportisa).start();
		
		
	}
}*/

}
