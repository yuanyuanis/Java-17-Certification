package com.yuanyuanis.concurrency.uni.u1.b_multihilo.h_productorConsumidor;

import java.util.LinkedList;
import java.util.Queue;

public class MemoriaCompartida {

    private Queue<Integer> cola;
    private int maxCapacity;

    public MemoriaCompartida(int maxCapacity) {
        this.cola = new LinkedList<>();
        this.maxCapacity = maxCapacity;
    }

    public synchronized Integer producir(Integer valor) {
        if(cola.size()< maxCapacity){
            cola.add(valor);
            valor++;
            return valor;
        }

        return null;
    }

    public synchronized Integer consumir() {
        if(cola.isEmpty() == false){
            return cola.poll();
        }
        return null;
    }
}
