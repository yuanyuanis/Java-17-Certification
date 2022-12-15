package com.yuanyuanis.concurrency.uni.u1.b_multihilo.b_threadStates;

public class TareaPrincipal implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Soy la TarePrincipal");
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}