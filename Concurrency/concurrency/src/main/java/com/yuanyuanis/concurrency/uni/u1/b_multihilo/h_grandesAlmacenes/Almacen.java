package com.yuanyuanis.concurrency.uni.u1.b_multihilo.h_grandesAlmacenes;

public class Almacen {

    private int numProductos;

    public Almacen(int nProductos) {
        this.numProductos = nProductos;
    }

    public synchronized boolean cogerProducto() {
        if (this.numProductos > 0) {
            this.numProductos--;
            return true;
        }
        return false;
    }
}
