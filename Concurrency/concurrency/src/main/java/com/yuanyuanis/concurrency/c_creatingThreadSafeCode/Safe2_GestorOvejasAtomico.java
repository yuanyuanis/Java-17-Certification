package com.yuanyuanis.concurrency.c_creatingThreadSafeCode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Safe2_GestorOvejasAtomico {
	
	private AtomicInteger contadorOvejas = new AtomicInteger(0);
	
	public  void incrementarYMostrar() {
		System.out.println("contadorOvejas: "+ contadorOvejas.incrementAndGet());
	}
	
	public static void main(String ...args) {
		
		ExecutorService service = Executors.newFixedThreadPool(20);
		
		Safe2_GestorOvejasAtomico gestor = new Safe2_GestorOvejasAtomico();
		
		for(int i =0;i<=10;i++){
			service.execute(()-> gestor.incrementarYMostrar());
		}
		service.shutdown();
	}

}
