package com.yuanyuanis.concurrency.uni.u1.ExecutorService;


import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceTestMethods {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

   //  testearMetodo_execute();
   //  testearMetodo_submitConRunnable();
   //  testearMetodo_submitConCallable();
      //   testearMetodo_invokeAny();
         testearMetodo_invokeAll();

    }

    private static void testearMetodo_invokeAll() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Callable<Integer> callable1 =() ->{
            return 10;
        };
        Callable<Integer> callable2 =() ->{
            return 20;
        };
        Callable<Integer> callable3 =() ->{
            return 30;
        };


        List<Callable<Integer>> coleccion = Arrays.asList(callable1, callable2, callable3);

        List<Future<Integer>> listaFuturos = executorService.invokeAll(coleccion);

        int resultadoTotal = 0;

        for(Future<Integer> future: listaFuturos){
            resultadoTotal = resultadoTotal + future.get();
        }
        System.out.println("El resultado es: "+resultadoTotal);
    }

    private static void testearMetodo_invokeAny() throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Callable<Integer> callable1 =() ->{
            return 10;
        };
        Callable<Integer> callable2 =() ->{
            return 20;
        };
        Callable<Integer> callable3 =() ->{
            return 30;
        };

        List<Callable<Integer>> coleccion = Arrays.asList(callable1, callable2, callable3);

        Integer resultado = executorService.invokeAny(coleccion);

        System.out.println("El resultado es: "+resultado);

        executorService.shutdown();

    }

    private static void testearMetodo_submitConCallable() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable unCallable = new UnCallable();

        Future<Integer> future = executorService.submit(unCallable);

        Integer resultado = future.get();

        System.out.println(" El resultado es: " + resultado);
    }









    private static void testearMetodo_submitConRunnable() throws InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {

                System.out.println(" Se esta ejecutando una tarea ... "+ i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Future future = executorService.submit(runnable);

        while(future.isDone() == false){
            Thread.sleep(500);
        }
        System.out.println("Fin de la cita ...");

        executorService.shutdown();
    }

    private static void testearMetodo_execute() {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Runnable runnable = () -> {
            System.out.println(" Se esta ejecutando una tarea");
        };

        executorService.execute(runnable);

        executorService.shutdown();
    }


}
