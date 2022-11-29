package com.yuanyuanis.concurrency.uni.u1.x_feedback1;

/**
 * Realiza una aplicación que ejecute 4 hilos de forma que se ejecuten de forma ordenada uno detrás
 * de otro esperando cada uno a que termine el anterior para ejecutarse
 */
public class Exercice3 {

    public static void main(String ...args) throws InterruptedException {

        Runnable runnable = () -> System.out.println(Thread.currentThread().getName() + " finish job");

        Thread t1 = new Thread(runnable, "T1");
        Thread t2 = new Thread(runnable, "T2");
        Thread t3 = new Thread(runnable, "T3");
        Thread t4 = new Thread(runnable, "T4");

        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();
        t4.start();
        t4.join();

        System.out.println("-----------------------------------------------------------");
        System.out.println(" Usamos join para forzar al hilo actual a forzar a terminar la tarea antes de continuar con otra");
        System.out.println("-----------------------------------------------------------");

    }
}
