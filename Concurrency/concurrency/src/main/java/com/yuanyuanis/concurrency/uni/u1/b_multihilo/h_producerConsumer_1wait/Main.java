package com.yuanyuanis.concurrency.uni.u1.b_multihilo.h_producerConsumer_1wait;

public class Main {
     public  static void main(String ...args) throws InterruptedException {

         ProductorConsumidorVersionConWaitYNotify productorConsumidor = new ProductorConsumidorVersionConWaitYNotify(10);

         Runnable consumidor = () -> {
             try {
                 productorConsumidor.consumir();
             } catch (InterruptedException e) {
                 return;
             }
         };

         Runnable producir = () -> {
             try {
                 productorConsumidor.producir();
             } catch (InterruptedException e) {
                 return;
             }
         };

         Thread t1 = new Thread(consumidor);
         Thread t2 = new Thread(producir);

         // Empezamos los dos hilos
         t1.start();
         t2.start();
         // Forzamos a que t1 finalice antes que t2
         t1.join();
         t2.join();

     }
}
