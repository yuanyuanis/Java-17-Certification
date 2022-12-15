package com.yuanyuanis.concurrency.uni.u1.b_multihilo.h_productorConsumidor;

public class Productor implements Runnable{

    private MemoriaCompartida memoriaCompartida;

    public Productor(MemoriaCompartida memoriaCompartida) {
        this.memoriaCompartida = memoriaCompartida;
    }

    @Override
    public void run() {
        int valor = 0;
        while(true){
            Integer valorProducido = memoriaCompartida.producir(valor);
            if(valorProducido!= null) {
                System.out.println(" El productor produce: " + valorProducido);
                valor = valorProducido;
                dormir(5);
            }
        }
    }

    private void dormir(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
