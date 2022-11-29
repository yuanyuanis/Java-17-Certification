package com.yuanyuanis.concurrency.uni.u1.a_multiproceso;


public class Sumador {



    public static int sumar(int n1, int n2){
        int suma = 0;
        for (int i = n1; i <= n2; i ++){
            suma = suma + i;
        }
        return suma;
    }


    public static void main(String[] args) {
        int n1 = Integer.parseInt(args[0]);
        int n2 = Integer.parseInt(args[1]);
        int suma = sumar(n1, n2);
        System.out.println(suma);
        System.out.flush();
    }


}
