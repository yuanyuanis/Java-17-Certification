package com.yuanyuanis.concurrency.uni.u1.b_multihilo.h_grandesAlmacenes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class MainGrandesAlmacenes {

    private static final int NUM_CLIENTES  = 300;
    private static final int NUM_PRODUCTOS = 100;

    public static void main(String[] args) {

        // Variables compartidas
        Almacen     almacen =   new Almacen(NUM_PRODUCTOS);
        ReentrantLock puerta  =   new ReentrantLock();

        List<Thread> hilosCliente = new ArrayList<>();


        for (int i=0; i<NUM_CLIENTES; i++){
            var runnable = new Cliente(puerta, almacen, "Cliente "+i);
            var t   = new Thread(runnable);
            t.start();
        }

        //Una vez arrancados esperamos a que todos terminen
        hilosCliente.forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
