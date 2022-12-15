package com.yuanyuanis.concurrency.uni.u1.b_multihilo.h_productorConsumidor_ArrayBloquing;

import org.jetbrains.annotations.Nullable;

import java.util.Queue;

public class Consumidor implements Runnable {

    private Queue<Integer> cola;

    Consumidor(Queue<Integer> cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (cola.isEmpty() == false) {
                    Integer valor = cola.poll();
                    System.out.println(" el Consumidor consume");

                    Thread.sleep(200);

                } else {
                    Thread.sleep(100);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
