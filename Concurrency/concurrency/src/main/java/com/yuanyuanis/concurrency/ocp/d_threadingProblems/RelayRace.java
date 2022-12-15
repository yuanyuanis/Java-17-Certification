package com.yuanyuanis.concurrency.ocp.d_threadingProblems;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class RelayRace {
    public static void main(String[] args) {
        int numRunners = 10; // número de corredores en la carrera

        // Creamos una barrera cíclica que señaliza cuando todos los corredores han llegado al punto de encuentro
        CyclicBarrier barrier = new CyclicBarrier(numRunners, new Runnable() {
            @Override
            public void run() {
                // Este código se ejecuta cuando todos los corredores han llegado al punto de encuentro
                System.out.println("¡Todos los corredores han llegado! ¡Continuemos la carrera!");
            }
        });

        // Creamos y arrancamos los corredores
        for (int i = 0; i < numRunners; i++) {
            new Thread(new Runner(barrier)).start();
        }
    }
    static class Runner implements Runnable {
        private CyclicBarrier barrier;

        public Runner(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println("Corredor " + Thread.currentThread().getId() + " ha comenzado la carrera");

                // Corremos una cierta distancia
                Thread.sleep(1000);

                System.out.println("Corredor " + Thread.currentThread().getId() + " ha llegado al punto de encuentro");

                // Esperamos a que todos los demás corredores lleguen al punto de encuentro
                barrier.await();

                // Continuamos la carrera
                System.out.println("Corredor " + Thread.currentThread().getId() + " ha continuado la carrera");

                // Corremos hasta el final
                Thread.sleep(1000);

                System.out.println("Corredor " + Thread.currentThread().getId() + " ha terminado la carrera");
            } catch (InterruptedException ex) {
                // manejamos la excepción
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
