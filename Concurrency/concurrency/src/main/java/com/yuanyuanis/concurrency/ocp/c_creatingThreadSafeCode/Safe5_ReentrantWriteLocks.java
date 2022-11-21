package com.yuanyuanis.concurrency.ocp.c_creatingThreadSafeCode;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 *
 * Bloqueo especial con una estrategia diferente: permite múltiples lectores
 * simultáneamente y un solo escritor.
 *
 * ReadLock readLock = rwLock.readLock();
 *
 * WriteLock writeLock = rwLock.writeLock();
 *
 * El acceso de lectura se otorga si no hay un escritor o un escritor solicita
 * acceso.
 *
 * Se otorga acceso de escritura si no hay Reader.
 *
 * ReentrantReadWriterLock brinda capacidades de reentrada para ReadWriteLock
 *
 * Feria en constructora:
 *
 * verdadero: Bloqueo justo, los subprocesos de nueva solicitud se ponen en cola
 * si se mantiene el bloqueo.
 *
 * falso: Bloqueo injusto: si se mantiene el bloqueo, los subprocesos que
 * solicitan pueden 'saltar' el cola de espera (predeterminada, especialmente
 * para bloqueo de escritura).
 *
 */
public class Safe5_ReentrantWriteLocks {
	
	private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private String miContenido = "Un largo contenido por defecto .......";
	
	/**
	 * Forma simple de usarlo
	 */
	public String leerContenido() {
		
		ReadLock readLock = readWriteLock.readLock();
		readLock.lock();
		try {
			System.out.println("Reading el estado mientras tengo el lock");
			return miContenido;
		} finally {
			readLock.unlock();
		}
	}
	
	public void escribirContenido(String contenidoAAnadir) {
		
		WriteLock writeLock = readWriteLock.writeLock();
		writeLock.lock();
		try {
			System.err.println("Writing " + contenidoAAnadir);
			miContenido = new StringBuilder(miContenido).append(contenidoAAnadir).toString();
			
		} finally {
			writeLock.unlock();
		}
	}

	public static void main(String[] args) {
		
		var executor = Executors.newCachedThreadPool();
		var self = new Safe5_ReentrantWriteLocks();
		
		// Lectores
		for (int i = 0; i < 100; i++) {
			executor.submit(() -> {
				// Deley de los readers para empezar.
				try {
					Thread.sleep(new Random().nextInt(10) * 100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println(self.leerContenido());
			});
		}
		
		// Escriores
		for (int i = 0; i < 5; i++) {
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			executor.execute(() -> self.escribirContenido(UUID.randomUUID().toString()));
			
		}

	}

}
