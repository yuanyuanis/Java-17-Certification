package com.yuanyuanis.concurrency.ocp.b_creatingthreadsconcurrencyapi;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;



public class Executors9_BeeperControl {
	
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public void hazBeepPorUnaHora() {
		
		Runnable beeper = () -> System.out.println("beep");

		System.out.println("Inicializando Beeper, espere por favor ...");
		// Ejecuta beeper con un delay de 5 segundos hace beep cada 2
		ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 5, 2, SECONDS);
		
		scheduler.schedule(() -> beeperHandle.cancel(true), 60 * 60, SECONDS); // Paramos el proceso a la hora.
	}
	
	public static void main(String ...args) {
		
		var self = new Executors9_BeeperControl();
		
		self.hazBeepPorUnaHora();
	}
}
