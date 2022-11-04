package com.yuanyuanis.concurrency.b_creatingthreadsconcurrencyapi;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import org.junit.jupiter.api.Test;

public class Executors9_BeeperControl {
	
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public void hazBeepPorUnaHora() {
		
		Runnable beeper = () -> System.out.println("beep");
		
		// Con un delay de 10 segundos hace beep cada 5
		final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 10, 5, SECONDS); 
		
		scheduler.schedule(() -> beeperHandle.cancel(true), 60 * 60, SECONDS); // Paramos el proceso a la hora.
	}
	
	public static void main(String ...args) {
		
		var self = new Executors9_BeeperControl();
		
		self.hazBeepPorUnaHora();
	}
}