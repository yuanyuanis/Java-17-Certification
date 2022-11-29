package com.yuanyuanis.concurrency.uni.u1.b_multihilo.e_philosophers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;

public class Table implements Runnable {

    private List<Philosopher> philosopherList;
    private List<Fork> forkList;
    public Iterator<Long> times;

    public Table(int numPhilosophers){
        if(numPhilosophers<2){
            throw new IllegalArgumentException("no puede haber menos de dos filosofos");
        }
        philosopherList = new ArrayList<>();
        forkList = new ArrayList<>();
        this.times = new Random().longs(2000, 7000).iterator();
        for (int i = 0; i < numPhilosophers; ++i) {

            forkList.add(new Fork());
        }
        for (int i = 0; i < numPhilosophers; ++i) {

            int n =(i+1)%numPhilosophers;
            Fork right = forkList.get(i);
            Fork left = forkList.get(n);
            boolean isLeftHanded = (n == 0);
            philosopherList.add(new Philosopher(this, "Philosopher "+i,right, left,isLeftHanded ));
        }

    }

    public synchronized long getTime() {
        return times.next();
    }

    @Override
    public void run() {
        var executorService = Executors.newFixedThreadPool(forkList.size());
        philosopherList.forEach((p) -> executorService.submit(p));
    }
}
