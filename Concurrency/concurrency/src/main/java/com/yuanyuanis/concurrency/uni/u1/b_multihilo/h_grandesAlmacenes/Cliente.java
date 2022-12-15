package com.yuanyuanis.concurrency.uni.u1.b_multihilo.h_grandesAlmacenes;

import java.util.concurrent.locks.ReentrantLock;

public class Cliente implements Runnable {

    private ReentrantLock puerta;  // Solo una puerta
    private Almacen almacen;       // Solo un almacén
    private String name;           // Nombre único del Thread

    private static final int MAX_INTENTOS = 10;

    Cliente(ReentrantLock puerta, Almacen almacen, String name) {
        this.puerta = puerta;
        this.almacen = almacen;
        this.name = name;
    }

    @Override
    public void run() {

        for (int i = 0; i < MAX_INTENTOS; i++) {

            if (puerta.tryLock()) {
                try {
                    if (almacen.cogerProducto()) {
                        System.out.println(name + ": cogí un producto!");
                        return;
                    } else {
                        System.out.println(name + ": ops, crucé pero no cogí nada");
                    }
                } finally {
                    puerta.unlock();
                }
            }
        }
        //Se superó el máximo de reintentos y abandonamos
        System.out.println(name + ": lo intenté muchas veces y no pude");
    }
}
