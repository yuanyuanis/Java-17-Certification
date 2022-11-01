package com.yuanyuanis.concurrency.c_creatingThreadSafeCode;


import java.util.concurrent.Executors;

public class Safe6_GestionHabitacionLeones {

    private void sacarLeones() {
        System.out.println("Sacando leones");
    }

    private void limpiarHabitacion() {
        System.out.println("Lipiando la habitacion");
    }

    private void anadiendoLeones() {
        System.out.println("Añadiendo leones");
    }

    public void performTask() {
    	sacarLeones();
    	limpiarHabitacion();
    	anadiendoLeones();
    }

    public static void main(String[] args) {
        var service = Executors.newFixedThreadPool(4);
        try {
            var manager = new Safe6_GestionHabitacionLeones();
            for (int i = 0; i < 4; i++)
                service.submit(() -> manager.performTask());
        } finally {
            service.shutdown();
        }
    }
}
