package com.yuanyuanis.concurrency.uni.u1.b_multihilo.d_conceptosTeoricos.isAlive;

public class Main {

    public static void main(String ...args){

        // Runnables

        TareaPrincipal tareaPrincipal = new TareaPrincipal();
        Thread hiloPrincipal = new Thread(tareaPrincipal);

        TareaAlive tareaAlive = new TareaAlive(hiloPrincipal);
        Thread hiloAlive = new Thread(tareaAlive);

        hiloPrincipal.start();
        hiloAlive.start();

        System.out.println("Se han terminado los dos hilos?");


    }
}
