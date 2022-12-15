package com.yuanyuanis.concurrency.uni.u1.b_multihilo.h_producerConsumer_1wait;

import java.util.LinkedList;

public class ProductorConsumidorVersionConWaitYNotify {
    private LinkedList<Integer> list = new LinkedList<>();
    private int capacity;

    ProductorConsumidorVersionConWaitYNotify(int capacity){

        this.capacity = capacity;
    }

    void consumir() throws InterruptedException {
        synchronized (this)
        {
            while(true) {
                while (list.size() == 0) {    // Espera si la lista esta vacia
                    wait();
                }

                int valor = list.removeFirst();
                System.out.println("Consumidor consume: " + valor);

                notify();                      // Notifica al productor que ahora puede producir

                Thread.sleep(1000);
            }
        }
    }

    void producir() throws InterruptedException {

        int cont = 0;
        while(true){
            synchronized (this){
                while(list.size() == capacity) {
                    wait();
                }
                System.out.println("Productor produce: "+ cont);
                list.add(cont++);

                notify();                   // Notifica al consumidor que ahora puede consumir

                Thread.sleep(1000);   // Esperamos un segundo en cada operación solo para hacer el problema más sencillo de entender
            }
        }
    }
}
