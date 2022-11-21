package com.yuanyuanis.concurrency.ocp.c_creatingThreadSafeCode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Safe_GestorOvejas {
	
	private  int contadorOvejas;
	
	public  void incrementarYMostrar() {
		System.out.println("contadorOvejas: "+ ++contadorOvejas);
	}
	
	public static void main(String ...args) {
		
		ExecutorService service = Executors.newFixedThreadPool(20);
		
		Safe_GestorOvejas gestor = new Safe_GestorOvejas();
		
		for(int i =0;i<=10;i++){
			service.execute(()-> gestor.incrementarYMostrar());
		}
		
		service.shutdown();
		
	}

}
