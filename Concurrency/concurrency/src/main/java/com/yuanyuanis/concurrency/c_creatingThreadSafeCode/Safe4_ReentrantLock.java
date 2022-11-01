package com.yuanyuanis.concurrency.c_creatingThreadSafeCode;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * Una alternativa a los locks intrinsecas, con las mismas garantías en lo que
 * respecta a serialización y visibilidad.
 * 
 * Lock es una interfaz con diferentes implementaciones que cambian el
 * comportamiento.
 * 
 * Ofrece cancelacion, fairness( justicia)
 * 
 * Justo en constructor:
 *
 * TRUE: Bloqueo justo, los subprocesos de nueva solicitud se ponen en cola si
 * alguien posee el lock.
 *
 * FALSE: Bloqueo injusto: si se posee el lock, los subprocesos que solicitan
 * pueden 'saltar' la cola de espera.
 *
 */
public class Safe4_ReentrantLock {

	private ReentrantLock bloqueo = new ReentrantLock();
	private boolean estado;

	/**
	 * Forma mas fácil de usar
	 */
	public void bloqueaMiCasa() {

		bloqueo.lock();

		try {
			System.out.println("Cambiando el estado del bloqueo de mi hogar ...");
			estado = !estado;
			System.out.println("El estado es: " + estado);

		} finally {
			bloqueo.unlock();
		}
	}

	/**
	 * TryLock -> Cogemos el Lock de despues de un tiempo, devuelve boolean.
	 * 
	 * @throws InterruptedException
	 */
	public void bloqueaMiCasaConUnTiempo() throws InterruptedException {

		// Tratamos de adquirir el lock después de un tiempo especifico.
		if (!bloqueo.tryLock(1l, TimeUnit.SECONDS)) {
			System.out.println("Fallo al adquirir el lock, otro Thread lo posee");
		} else {

			try {
				System.out.println("Simulando un cómputo de bloqueo - obligando a tryLock() a fallar");
				Thread.sleep(3000);

			} finally {
				bloqueo.unlock();
			}

		}

	}

	public static void main(String[] args) {
		
		var executor = Executors.newCachedThreadPool();
		var self = new Safe4_ReentrantLock();
		
		for (int i = 0; i < 10; i++) {
			executor.execute(() -> self.bloqueaMiCasa());
		}
		
		for (int i = 0; i < 40; i++) {
			executor.execute(() -> {
				try {
					self.bloqueaMiCasaConUnTiempo();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		
		executor.shutdown();
	}

}
