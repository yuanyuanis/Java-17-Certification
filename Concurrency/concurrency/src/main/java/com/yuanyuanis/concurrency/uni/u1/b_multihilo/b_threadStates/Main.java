package com.yuanyuanis.concurrency.uni.u1.b_multihilo.b_threadStates;

public class Main {

    public static void main(String ...args) {

        Runnable tareaPrincipal = new TareaPrincipal();

        Thread hilo1 = new Thread(tareaPrincipal);

        Runnable tareaAlive = new TareaAlive(hilo1) ;

        Thread hilo2 = new Thread(tareaAlive);

        hilo1.start();
        hilo2.start();




    }
}
