package com.yuanyuanis.concurrency.ocp.c_creatingThreadSafeCode;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Safe7_GestionHabitacionLeonesCyclicBarrier {

	private void sacarLeones() {
		System.out.println(Thread.currentThread().getName() + ": Sacando leones");
	}

	private void limpiarHabitacion() {
		System.out.println(Thread.currentThread().getName() + ": Limpiando la habitacion");
	}

	private void anadiendoLeones() {
		System.out.println(Thread.currentThread().getName() + ": Añadiendo leones");
	}

	public void realizarTarea(CyclicBarrier c1, CyclicBarrier c2) {

		try {
			sacarLeones();
			c1.await();
			limpiarHabitacion();
			c2.await();
			anadiendoLeones();

		} catch (InterruptedException | BrokenBarrierException e) {

		}

	}

	public static void main(String[] args) {

		var gestor = new Safe7_GestionHabitacionLeonesCyclicBarrier();

		CyclicBarrier c1 = new CyclicBarrier(4);
		CyclicBarrier c2 = new CyclicBarrier(4, () -> System.out.println("habitacion limpia!!!!!!"));

		Runnable runnable = () -> gestor.realizarTarea(c1, c2);

		Thread trabajador1 = new Thread(runnable, "Trabajador 1");
		Thread trabajador2 = new Thread(runnable, "Trabajador 2");
		Thread trabajador3 = new Thread(runnable, "Trabajador 3");
		Thread trabajador4 = new Thread(runnable, "Trabajador 4");

		trabajador1.start();
		trabajador2.start();
		trabajador3.start();
		trabajador4.start();

		/*
		 * var service = Executors.newFixedThreadPool(4); try { var manager = new
		 * Safe7_GestionHabitacionLeonesCyclicBarrier(); var c1 = new CyclicBarrier(4);
		 * var c2 = new CyclicBarrier(4, () -> System.out.println("*** Pen Cleaned!"));
		 * for (int i = 0; i < 4; i++) service.submit(() -> manager.realizarTarea(c1,
		 * c2)); } finally { service.shutdown(); }
		 */

	}
}
