package com.yuanyuanis.concurrency.b_creatingthreadsconcurrencyapi;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Executors3_Future_CalculadorCuadrado_Multithreading {
	
	private ExecutorService service = Executors.newSingleThreadExecutor();
	
	public Future<Integer> caculaCuadrado(Integer valor){
		
		return service.submit(() -> {
			System.out.println("Calculando el cuadrado de: "+ valor);
			Thread.sleep(1000);
			return valor*valor;
		});
	}
	
	public static void main(String ...args) throws InterruptedException, ExecutionException {
		
		var calculadorCuatrado = new Executors3_Future_CalculadorCuadrado_Multithreading();
		
		
		Future<Integer> future1 = calculadorCuatrado.caculaCuadrado(4);
		Future<Integer> future2 = calculadorCuatrado.caculaCuadrado(100);
		
		
		while(!(future1.isDone() && future2.isDone())) {
			System.out.println(
				String.format("Future1 está %s u Future2 está %s ",
					future1.isDone()? "[Done] ": " [Calculando ...]",
					future2.isDone()? "[Done] ": " [Calculando ...]"
				)
			);
			Thread.sleep(300);
		}
		

		System.out.println("ResultadoF1: " + future1.get());
		System.out.println("ResultadoF2: " + future2.get());
		
		calculadorCuatrado.service.shutdown();
	}

}
