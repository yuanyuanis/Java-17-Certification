package com.yuanyuanis.concurrency.uni.u1.b_multihilo.d_conceptosTeoricos.creacionyejecucion;

public class Tarea extends Thread{


    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()  +" :Se está ejecutando nuestra tarea");
    }

    public static void main (String []args){

        Runnable tarea = new Tarea();
        Thread thread = new Thread(tarea, "PACO");

        thread.start();

        System.out.println(Thread.currentThread().getName() + " :Fin");

    }
}
