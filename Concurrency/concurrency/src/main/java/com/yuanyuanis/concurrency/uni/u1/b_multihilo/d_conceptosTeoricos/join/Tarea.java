package com.yuanyuanis.concurrency.uni.u1.b_multihilo.d_conceptosTeoricos.join;

public class Tarea implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " está ejecutando su tarea: " + i);
        }
    }

    public static void main(String ...args) throws InterruptedException {

        Runnable tarea =  new Tarea();

        Thread caminero = new Thread(tarea, "caminero");
        Thread guti = new Thread(tarea, "guti");

        caminero.start();
        caminero.join();

        guti.start();
        guti.join();

        System.out.println(Thread.currentThread().getName() + ": Fin");
    }
}
