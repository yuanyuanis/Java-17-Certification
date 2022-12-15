package com.yuanyuanis.concurrency.uni.u1.b_multihilo.h_grandesAlmacenes;

import java.util.ArrayList;

public class MainGrandesAlmacenesV2 {

    private static final int NUM_CLIENTES  = 300;
    private static final int NUM_PRODUCTOS = 100;

    public static void main(String[] args) {

        // Variables compartidas
        var almacen =   new Almacen(NUM_PRODUCTOS);
        Object puerta  =   new Object();

        var hilosCliente = new ArrayList<Thread>();


        for (int i=0; i<NUM_CLIENTES; i++){
            var runnable = new ClienteV2(puerta, almacen, "Cliente "+i);
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
