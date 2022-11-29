package com.yuanyuanis.concurrency.uni.u1.b_multihilo.c_threadsyncronizationErrors;

public class IncrementarMostruos implements Runnable{

    private Contador contador;

    public IncrementarMostruos (Contador contador){
        this.contador = contador;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10_000; i++) {
            contador.incrementar();
        }
    }

    public static void main(String ...args) throws InterruptedException {
        Contador cont = new Contador();
        Runnable runnable = new IncrementarMostruos(cont);

        Thread goku = new Thread(runnable);
        Thread krilin = new Thread(runnable);

        goku.start();
        krilin.start();

        goku.join();
        krilin.join();

        System.out.println("goku alive: " +goku.isAlive());
        System.out.println("krilin alive: " +krilin.isAlive());

        System.out.println("El contador es: " + cont.getCount());

    }
}
