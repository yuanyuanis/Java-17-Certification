package com.yuanyuanis.concurrency.uni.u1.b_multihilo.d_conceptosTeoricos.isAlive;

public class TareaPrincipal implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println( "Tarea principal se esta ejecutando" );

        }

    }
}
