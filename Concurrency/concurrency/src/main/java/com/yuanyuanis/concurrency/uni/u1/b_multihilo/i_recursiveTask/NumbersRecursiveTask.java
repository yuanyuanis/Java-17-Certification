package com.yuanyuanis.concurrency.uni.u1.b_multihilo.i_recursiveTask;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class NumbersRecursiveTask extends RecursiveTask<Integer> {
    private int[] arr;

    private static final int LIMITE = 20;

    public NumbersRecursiveTask(int[] arr) {
        this.arr = arr;
    }

    @Override
    protected Integer compute() {
        if (arr.length > LIMITE) {
            return ForkJoinTask.invokeAll(createSubtasks())
                    .stream()
                    .mapToInt(ForkJoinTask::join)
                    .sum();
        } else {
            return processing(arr);
        }
    }

    private Collection<NumbersRecursiveTask> createSubtasks() {
        List<NumbersRecursiveTask> dividedTasks = new ArrayList<>();
        dividedTasks.add(new NumbersRecursiveTask(
                Arrays.copyOfRange(arr, 0, arr.length / 2)));
        dividedTasks.add(new NumbersRecursiveTask(
                Arrays.copyOfRange(arr, arr.length / 2, arr.length)));
        return dividedTasks;
    }

    private Integer processing(int[] arr) {
        return Arrays.stream(arr)
                .filter(a -> a > 10 && a < 27)
                .map(a -> a * 10)
                .sum();
    }

    public static void main(String ...args){

        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        int[]  randomIntsArray = IntStream.generate(() -> new Random().nextInt(100)).limit(100).toArray();
        System.out.println(randomIntsArray.toString());
        var self = new NumbersRecursiveTask(randomIntsArray);
        int result = commonPool.invoke(self);

        System.out.println("Resultado: " + result);
    }
}
