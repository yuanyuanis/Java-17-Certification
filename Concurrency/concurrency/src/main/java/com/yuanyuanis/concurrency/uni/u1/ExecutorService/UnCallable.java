package com.yuanyuanis.concurrency.uni.u1.ExecutorService;

import java.util.concurrent.Callable;

public class UnCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {

        for (int i = 0; i < 10; i++) {

            System.out.println(" Se esta ejecutando  mi callable ... "+ i);
            Thread.sleep(500);

        }
        return 10;
    }
}
