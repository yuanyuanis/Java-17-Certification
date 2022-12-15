package com.yuanyuanis.concurrency.uni.u1.b_multihilo.c_threadsyncronizationErrors;

public class MatadorMostruos implements Runnable{

    private Contador contador;

    MatadorMostruos(Contador contador){
        this.contador = contador;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10_000; i++) {
            contador.incrementar();
        }

    }

    public static void main(String ...args) throws InterruptedException {

        Contador contadorRes = new Contador();

        Runnable runnable = new MatadorMostruos(contadorRes);

        Thread goku = new Thread(runnable);
        Thread krilin = new Thread(runnable);

        goku.start();
        krilin.start();

        goku.join();
        krilin.join();

        System.out.println("goku isAlive: "+ goku.isAlive() + "Krilin isAlive: " + krilin.isAlive());

        System.out.println("Contador: " + contadorRes.getCuenta());

    }
}
