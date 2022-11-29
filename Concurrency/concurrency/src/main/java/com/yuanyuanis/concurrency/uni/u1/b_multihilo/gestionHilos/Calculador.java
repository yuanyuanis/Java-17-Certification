package com.yuanyuanis.concurrency.uni.u1.b_multihilo.gestionHilos;

import java.util.Random;

public class Calculador implements Runnable {
    @Override
    public void run() {
        var num = 0;
        Thread thread = Thread.currentThread();
        while(num<5){
            System.out.println(thread.getName() +"esta calculando ...");
            long time = new Random().nextInt(5)*1000;
            if(time > 3000){
                System.out.println(
                        "Terminando hilo: "+
                                thread.getName()
                );
                try {
                    thread.join(); // BAD JOIN EXAMPLE
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Calculado y reiniciando.");
            num++;
        }
        System.out.println("Hilo terminado:"+thread.getName());
    }

    public static void main(String[] args) {
        Calculador vHilos[]=new Calculador[5];
        for (int i=0; i<5;i++){
            vHilos[i]=new Calculador();
            Thread hilo=new Thread(vHilos[i]);
            hilo.setName("Hilo "+i);
            if (i==0){
                hilo.setPriority(Thread.MAX_PRIORITY);
            }
            hilo.start();
        }
    }
}
