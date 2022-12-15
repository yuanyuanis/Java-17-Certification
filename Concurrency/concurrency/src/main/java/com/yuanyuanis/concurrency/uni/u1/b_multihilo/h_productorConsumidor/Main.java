package com.yuanyuanis.concurrency.uni.u1.b_multihilo.h_productorConsumidor;

public class Main {

    public static void main(String[] args) {

        MemoriaCompartida memoriaCompartida = new MemoriaCompartida(5);

        Runnable productor = new Productor(memoriaCompartida);
        Runnable consumidor = new Consumidor(memoriaCompartida);

        Thread thread1 = new Thread(productor);
        Thread thread2 = new Thread(consumidor);

        thread1.start();
        thread2.start();

    }
}
