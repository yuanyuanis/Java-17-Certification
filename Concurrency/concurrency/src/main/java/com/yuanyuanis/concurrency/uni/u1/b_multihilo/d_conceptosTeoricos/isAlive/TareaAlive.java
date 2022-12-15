package com.yuanyuanis.concurrency.uni.u1.b_multihilo.d_conceptosTeoricos.isAlive;

public class TareaAlive implements Runnable{

    private Thread otroHilo;

    TareaAlive(Thread otroThread){
        this.otroHilo = otroThread;
    }

    @Override
    public void run() {
        while (otroHilo.isAlive()) {
            System.out.println("Yo hago cosas mientras el otro hilo siga en ejecución");
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

        System.out.println("El otro hilo ha terminado. Yo también");
    }
}
