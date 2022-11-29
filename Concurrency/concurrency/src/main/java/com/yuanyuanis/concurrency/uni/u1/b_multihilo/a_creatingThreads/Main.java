package com.yuanyuanis.concurrency.uni.u1.b_multihilo.a_creatingThreads;

public class Main {

    public static void main(String ...args) throws InterruptedException {

        Thread tPaco = new Thread( new PrimeraTarea(), "PACO");
        Thread tMaria = new Thread(new PrimeraTarea(), "MARIA");

        tPaco.start(); //t1
        tMaria.start(); //t2

        Thread.sleep(100);

        // Esta línea con sleep se fuerza a que se imprima la última por pantalla.
        System.out.println("--- Hilo principal: " +Thread.currentThread().getName() +"---");

    }
}
