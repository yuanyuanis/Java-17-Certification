package com.yuanyuanis.concurrency.uni.u1.b_multihilo.h_productorConsumidor;

public class Consumidor implements Runnable{

    private MemoriaCompartida memoriaCompartida;

    public Consumidor(MemoriaCompartida memoriaCompartida) {
        this.memoriaCompartida = memoriaCompartida;
    }

    @Override
    public void run() {
        while (true){
            Integer valor = memoriaCompartida.consumir();
            if(valor!= null){
                System.out.println(" El consumidor ha consumido: " + valor);
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
