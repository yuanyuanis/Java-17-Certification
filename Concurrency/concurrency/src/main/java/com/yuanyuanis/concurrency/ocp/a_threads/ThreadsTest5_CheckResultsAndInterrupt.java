package com.yuanyuanis.concurrency.ocp.a_threads;

public class ThreadsTest5_CheckResultsAndInterrupt {
	
	private static int counter = 0;

	public static void main(String[] a) {
		
		var mainThread = Thread.currentThread();
		
		new Thread(() -> {
			for (int i = 0; i < 1_000_000; i++)
				counter++;
			mainThread.interrupt();
		}).start();

		while (counter < 1_000_000) {
			
			System.out.println("Not reached yet");
			try {
				Thread.sleep(1_000); // 1 SECOND
			} catch (InterruptedException e) {
				System.out.println("Interrupted!");
			}
		}
		
		/**
		 * Esta versión mejorada incluye tanto sleep(), para evitar sobrecargar la CPU, como interrupt(), 
		 * por lo que el trabajo del subproceso(thread work) finaliza sin retrasar el programa. 
		 * Como antes, el estado de nuestro subproceso main() alterna entre TIMED_WAITING y RUNNABLE. 
		 * Llamar a interrupt() en un subproceso en el estado TIMED_WAITING o WAITING hace que el subproceso principal() vuelva a ser RUNNABLE,
		 * lo que desencadena una InterruptedException. 
		 * El subproceso también puede pasar a un estado BLOQUEADO si necesita volver a adquirir recursos cuando se despierta.
		 */
		System.out.println("Reached: " + counter);
	}

}
