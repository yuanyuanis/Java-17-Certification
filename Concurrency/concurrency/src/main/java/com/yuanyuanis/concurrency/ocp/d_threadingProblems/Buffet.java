package com.yuanyuanis.concurrency.ocp.d_threadingProblems;

import java.util.concurrent.Semaphore;

public class Buffet {
    public static void main(String[] args) {
        int numPeople = 10; // número de personas en el buffet
        int timeLimit = 5; // tiempo límite en segundos para comer

        // Creamos un semáforo para controlar el acceso al buffet
        Semaphore buffet = new Semaphore(1);

        // Creamos y arrancamos las personas
        for (int i = 0; i < numPeople; i++) {
            new Thread(new Person(buffet, timeLimit), String.valueOf(i)).start();
        }
    }
}

class Person implements Runnable {
    private Semaphore buffet;
    private int timeLimit;

    public Person(Semaphore buffet, int timeLimit) {
        this.buffet = buffet;
        this.timeLimit = timeLimit;
    }

    @Override
    public void run() {
        try {
            // Intentamos obtener un permiso para entrar al buffet
            buffet.acquire();

            System.out.println("Persona " + Thread.currentThread().getName() + " ha entrado al buffet");

            // Comemos durante el tiempo límite
            Thread.sleep(timeLimit * 1000);

            // Dejamos el buffet
            System.out.println("Persona " + Thread.currentThread().getName() + " ha dejado el buffet");

            buffet.release();
        } catch (InterruptedException ex) {
            // manejamos la excepción
        }
    }
}
