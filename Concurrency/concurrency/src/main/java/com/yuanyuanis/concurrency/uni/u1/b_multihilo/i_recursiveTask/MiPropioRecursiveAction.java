package com.yuanyuanis.concurrency.uni.u1.b_multihilo.i_recursiveTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Logger;

public class MiPropioRecursiveAction extends RecursiveAction {

    private String texto = "";
    private static final int LIMITE = 4;

    private static Logger logger = Logger.getAnonymousLogger();

    public MiPropioRecursiveAction(String texto) {
        this.texto = texto;
    }

    @Override
    protected void compute() {
        if (texto.length() > LIMITE) {
            ForkJoinTask.invokeAll(crearSubtareas());
        } else {
            procesando(texto);
        }
    }

    private List<MiPropioRecursiveAction> crearSubtareas() {
        List<MiPropioRecursiveAction> subtareas = new ArrayList<>();

        String parteUno = texto.substring(0, texto.length() / 2);
        String parteDos = texto.substring(texto.length() / 2, texto.length());

        subtareas.add(new MiPropioRecursiveAction(parteUno));
        subtareas.add(new MiPropioRecursiveAction(parteDos));

        return subtareas;
    }

    private void procesando(String work) {
        String result = work.toUpperCase();
        logger.info("This result - (" + result + ") - was processed by "
                + Thread.currentThread().getName());
    }

    public static void main(String ...args){

        ForkJoinPool commonPool = ForkJoinPool.commonPool();

        var self = new MiPropioRecursiveAction("lajhgfjalsdfhkssjkhaskjdfhkjasdfhksajfhlskjfhaskjfhsakldjhlsakdfhsalkdfhsakldflksadfhlksadhflaskdhf");
        commonPool.invoke(self);
    }
}