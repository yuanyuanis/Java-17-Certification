package com.yuanyuanis.concurrency.uni.u1.b_multihilo.a_creatingThreads;

public class PrimeraTarea implements Runnable{

    @Override
    public void run() {



        for (int i = 0; i < 3; i++) {

            System.out.println(Thread.currentThread().getName() + " esta trabajando: " +i);
        }

    }
}
