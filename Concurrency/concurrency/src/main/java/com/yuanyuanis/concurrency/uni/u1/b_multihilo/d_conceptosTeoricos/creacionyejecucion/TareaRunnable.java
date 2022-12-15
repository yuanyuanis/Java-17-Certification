package com.yuanyuanis.concurrency.uni.u1.b_multihilo.d_conceptosTeoricos.creacionyejecucion;

public class TareaRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName()  +" :Se está ejecutando nuestra tarea " + i);
        }
    }
    public static void main (String []args) throws InterruptedException {

        TareaRunnable tareaRunnable = new TareaRunnable();
        Thread thread1 = new Thread(tareaRunnable, "RAUL");
        Thread thread2 = new Thread(tareaRunnable, "TORRES");

        thread1.start();
        Thread.sleep(115);
        thread2.start();

        System.out.println(Thread.currentThread().getName() + " :Fin");


    }
}
