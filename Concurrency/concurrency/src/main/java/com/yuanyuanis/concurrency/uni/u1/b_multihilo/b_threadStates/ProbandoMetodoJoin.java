package com.yuanyuanis.concurrency.uni.u1.b_multihilo.b_threadStates;

import com.yuanyuanis.concurrency.uni.u1.b_multihilo.a_creatingThreads.PrimeraTarea;

public class ProbandoMetodoJoin {
    public static void main(String ...args) throws InterruptedException {

        Thread t1 = new Thread(new PrimeraTarea(), "T1");
        Thread t2 = new Thread(new PrimeraTarea());
        t2.setName("t2");
        Thread t3 = new Thread(new PrimeraTarea(), "T3");

        t1.start();
        t1.join(10); // Le decimos al hilo main que espere 10ms a que se ejecute el hilo t1
        t2.start();
        t2.join(); //
        t3.start();
        t3.join();

        System.out.println("Se acaba el programa");


    }
}
