package com.yuanyuanis.concurrency.uni.u1.x_feedback1;

/**
 * Realiza una aplicación de texto que lance dos hilos de forma que el segundo se ejecute mientras dure la ejecución del primero
 */
public class Exercice2 {

    public static void main(String ...args) throws InterruptedException {
        Runnable task1 = () -> {
            System.out.println(Thread.currentThread().getName() + " .......... is doing task 1");
            System.out.println("------------------------"+Thread.currentThread().getName() + " is going to sleep ---------------------------");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " ........... finished task 1");
        };

        Runnable task2 = () -> {
            System.out.println(Thread.currentThread().getName() + " is doing task 2");
            System.out.println(Thread.currentThread().getName() + " finished task 2");
        };

        Thread t1 = new Thread(task1, "T1");
        Thread t2 = new Thread(task2, "T2");

        t1.start();
        Thread.currentThread().sleep(10); // Force t1 to start
        t2.start();


    }
}
