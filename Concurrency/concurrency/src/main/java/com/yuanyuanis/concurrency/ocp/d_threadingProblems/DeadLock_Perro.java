package com.yuanyuanis.concurrency.ocp.d_threadingProblems;

import java.util.concurrent.Executors;

public record DeadLock_Perro(String name) {
	
	public void comerYBeber(Comida comida, Bebida bebida) {
		synchronized (comida) {
			System.out.println(name() + " esta comiendo :-)");
			esperar();
			synchronized (bebida) {
				System.out.println(name() + " esta bebiendo :-)");
			}
		}
		
	}
	public void beberYComer(Comida comida, Bebida bebida) {
		synchronized (bebida) {
			System.out.println(name() + " esta bebiendo :-)");
			esperar();
			synchronized (comida) {
				System.out.println(name() + " esta comiendo :-)");
			}
		}
	}
	
	
	private void esperar() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String ...args) {
		
		var perritoToby = new DeadLock_Perro("Toby!");
		var perritoLeyla = new DeadLock_Perro("Leyla!");

		Comida comida = new Comida();
		Bebida bebida = new Bebida();
		
		var service = Executors.newFixedThreadPool(3);
		
		try {
			service.submit(() -> perritoToby.comerYBeber(comida, bebida));
			service.submit(() -> perritoLeyla.beberYComer(comida, bebida));
		} finally {
			service.shutdown();
		}
		
	}
	
	static class Comida{
		
	}
	
	static class Bebida{
		
	}
	
	

}
