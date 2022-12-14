package com.yuanyuanis.concurrency.uni.u1.a_multiproceso;

import java.io.File;
import java.io.IOException;

public class ThrowSum {

    public static void main(String[] args) throws IOException {
        ThrowSum l = new ThrowSum();
        l.lanzarSumador(1, 5, "resultado1.txt");
        l.lanzarSumador(6,10, "resultado2.txt");
        System.out.println("Ok");
    }

    public void lanzarSumador(Integer n1, Integer n2, String fichResultado) throws IOException {

        System.out.println(Sumador.class.getCanonicalName());


        // 1) Crear proceso.
        ProcessBuilder pb = new ProcessBuilder("java", Sumador.class.getCanonicalName(),
                n1.toString(),
                n2.toString());


        pb.redirectError(new File("errores.txt"));
        pb.redirectOutput(new File(fichResultado));

        // 2) Arrancar proceso.
        pb.start();
    }
}
