package com.yuanyuanis.concurrency.uni.u1.b_multihilo.c_threadsyncronizationErrors;

import java.util.concurrent.atomic.AtomicInteger;

class Contador {
    private AtomicInteger contador = new AtomicInteger(0);

    public  void incrementar() {

        contador.incrementAndGet();

    }

    public AtomicInteger getCuenta() {
        return contador;
    }
}
