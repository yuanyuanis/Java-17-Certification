package com.yuanyuanis.concurrency.ocp.c_creatingThreadSafeCode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Safe3_GestorOvejasSincronizado {
	
	private int contadorOvejas;
	
	public  void incrementarYMostrar() {
		synchronized (this) {

			System.out.println("contadorOvejas: "+ ++contadorOvejas);
		}
			
	}
	
	public static void main(String ...args) {
		
		ExecutorService service = Executors.newFixedThreadPool(20);
		
		Safe3_GestorOvejasSincronizado gestor = new Safe3_GestorOvejasSincronizado();
		
		for(int i =0;i<=10;i++){
			service.execute(()-> gestor.incrementarYMostrar());
		}
		service.shutdown();
	}

}
