package com.yuanyuanis.concurrency.uni.u1.b_multihilo.d_conceptosTeoricos.creacionyejecucion;

public class InstanciandoRunnables {

    public static void main(String ...args){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable number 1");
            }
        };

        Runnable runnable2 = new PruebaRunnable();

        Runnable runnable3 = () -> System.out.println("Runnable number 3");

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable2);
        Thread t3 = new Thread(runnable3);

        t1.start();
        t2.start();
        t3.start();
    }

    static class PruebaRunnable implements  Runnable{

        @Override
        public void run() {
            System.out.println("Runnable number 2");
        }
    }
}
