package com.yuanyuanis.concurrency.uni.u1.b_multihilo.h_productorConsumidor_ArrayBloquing;

import java.util.Queue;

public class Productor implements Runnable{

    private Queue<Integer> cola;

    Productor(Queue<Integer> cola) {
        this.cola = cola;
    }
    @Override
    public void run() {
        try {
            int valor = 0;
            while(true) {
                if(cola.offer(valor)) {
                    System.out.println(" El productor produce: " + valor);
                    valor++;
                    Thread.sleep(200);

                }else{
                    System.out.println("--------- OJO LA COLA ESTA LLENA ----  "+ cola.size());
                    Thread.sleep(100);
                }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
