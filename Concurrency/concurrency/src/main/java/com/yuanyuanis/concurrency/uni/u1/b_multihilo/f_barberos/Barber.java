package com.yuanyuanis.concurrency.uni.u1.b_multihilo.f_barberos;

import java.util.concurrent.Semaphore;


/*  Hay un peluquero durmiendo en su tienda. Cuando llega un cliente, comprueba si el peluquero está durmiendo y lo despierta.
    Si no hay otros clientes en la sala de espera, el barbero se corta el pelo.
    De lo contrario, el cliente toma un juego en la sala de espera. Pero si no hay asientos, se va.
    Una vez que un barbero termina de cortar el cabello, verifica si hay clientes en la sala de espera. Si no, se vuelve a dormir de nuevo.
    La idea es que el peluquero trabaje solo cuando llega un cliente y duerma en caso contrario.
 */

class Barber {
    static Semaphore barber;
    static Semaphore customer;
    static Semaphore mutex;            // Solo un sitio / se levanta de pronto
    static int seats = 2;                    // Número de sitios en la sala de espera.
    static int N = 5;                        // Numero de clientes.


    // La barbería provee de servicio 24x7 :
    // 1. El barbero duerme hasta que es levantado por un customer
    // 2. El barbero elige el primer cliente de la sala (b.release)
    // 3. One seat is freed up (seats++)
    // 4. Cuts customer's hair (sleep 1s)
    // ... Goes back to sleep
    static void barber() {
        new Thread(() -> {
            try {
                while (true) {
                    log("Barbero: ... sleeping");
                    customer.acquire();                     // wait for customer
                    log("Barbero: ... coge a cliente");

                    mutex.acquire();                        // wait for available seats
                    barber.release();                       // signal Barber ready
                    seats++;
                    mutex.release();                        // signal mutex

                    log("Barbero: ... cortando el pelo");
                    Thread.sleep(1000);
                    log("Barbero: ... Fin corte");
                }
            } catch (InterruptedException e) {
            }
        }).start();
    }

    // Each customer requires a haircut:
    // 1. Occupies a seat, if available
    // 2. Wakes up the barber (c.release)
    // 3. Waits for barber to indicate his turn
    // ... His hair is cut.
    static void customer(int i) {
        new Thread(() -> {
            try {
                log(i + ": checking seats");
                mutex.acquire();
                if (seats <= 0) {
                    log(i + ": no seats!");
                    mutex.release();
                    return;
                }
                seats--;
                customer.release();                             //
                mutex.release();
                log(i + ": sat, seats=" + seats);
                barber.acquire();                               // wait barber
                log(i + ": having hair cut");
            } catch (InterruptedException e) {
            }
        }).start();
    }

    // 1. Barber is sleeping
    // 2. There are no customers
    // 3. No one is accessing seats
    // 4. Baber is started (sleeping)
    // 5. After random intervals, customers arrive
    public static void main(String[] args) {
        log("Starting barber (B) with " +
                seats + " seats" + " and " + N + " customers ...");
        barber = new Semaphore(0);
        customer = new Semaphore(0);
        mutex = new Semaphore(1);
        barber();
        for (int i = 0; i < N; i++) {
            sleep(1000 * Math.random());
            customer(i);
        }
    }

    static void sleep(double t) {
        try {
            Thread.sleep((long) t);
        } catch (InterruptedException e) {
        }
    }

    static void log(String x) {
        System.out.println(x);
    }
}
