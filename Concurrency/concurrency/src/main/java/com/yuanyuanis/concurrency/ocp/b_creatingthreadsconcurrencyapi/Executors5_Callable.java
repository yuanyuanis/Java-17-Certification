package com.yuanyuanis.concurrency.ocp.b_creatingthreadsconcurrencyapi;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

import java.lang.management.ManagementFactory;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Executors5_Callable implements Callable<String>{
	
	private static final int NUM_PROCESADORES = Runtime.getRuntime().availableProcessors();

	@Override
	public String call() throws Exception {
		sleep(1000);
		return currentThread().getName();
	}
	
	public static void main(String ...args) {
		
	    System.out.println("Total Number of threads " + ManagementFactory.getThreadMXBean().getThreadCount());
		
		Instant start = Instant.now();
		
		ExecutorService service = Executors.newFixedThreadPool(NUM_PROCESADORES);
		
		var list = new ArrayList<Future<String>>();
		
		var myCallable = new Executors5_Callable();
		
		for(int i = 0; i <= 100; i++) {
			Future<String> future = service.submit(myCallable);
			list.add(future);
		
		}
		
		for (Future<String> future : list) {
			
			// Esto se queda esperando a una respuesta Future.get().
			try {
				System.out.println(new Date()+"::"+future.get());
			} catch (InterruptedException | ExecutionException e) {
			}
			
		}
		
		
		Instant end = Instant.now();
		Duration dur = Duration.between(start, end);
		System.out.println("Se ha tardado en ejecutar el código: "+ dur);
		
		service.shutdown();
		
	}

}
