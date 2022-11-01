package com.yuanyuanis.concurrency.b_creatingthreadsconcurrencyapi;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Executors2_Future_CalculadorCuadrado {
	
	private ExecutorService service = Executors.newSingleThreadExecutor();
	
	public Future<Integer> caculaCuadrado(Integer valor){
		
		return service.submit(() -> {
			Thread.sleep(1000);
			return valor*valor;
		});
	}
	
	public static void main(String ...args) throws InterruptedException, ExecutionException {
		
		var calculadorCuatrado = new Executors2_Future_CalculadorCuadrado();
		
		Future<Integer> future = calculadorCuatrado.caculaCuadrado(22);
		
		while(!future.isDone()) {
			System.out.println("Calculando ...");
			Thread.sleep(300);
		}
		
		Integer resultado = future.get();
		System.out.println("Resultado: " + resultado);
		
		calculadorCuatrado.service.shutdown();
	}

}
