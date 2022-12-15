package com.yuanyuanis.concurrency.uni.u1.b_multihilo.h_productorConsumidor_ArrayBloquing;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    public static void main(String ...args) {

        Queue<Integer> cola = new ArrayBlockingQueue<>(5);

        Runnable consumidor = new Consumidor(cola);
        Runnable productor = new Productor(cola);

        Thread h1 = new Thread(consumidor);
        Thread h2 = new Thread(productor);

        h1.start();
        h2.start();

    }
}
