package com.yuanyuanis.concurrency.b_creatingthreadsconcurrencyapi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executors1_SingleThread {

	public static void main(String[] args) {
		
		Runnable imprimeInventario = () -> System.out.println("imprimiendo inventario ZOO");
		Runnable imprimirRegistro = () -> {
			for (int i = 0; i <= 3; i++) {
				System.out.println("Imprimiendo Registro: "+i);
			}
		};
		
		ExecutorService service = Executors.newSingleThreadExecutor();
		
		try {
			System.out.println("Comienza ...");
			
			service.execute(imprimeInventario);
			service.execute(imprimirRegistro);
			service.execute(imprimeInventario);
			
			System.out.println("Fin ...");

		}finally {
			service.shutdown();
		}
	}
}
