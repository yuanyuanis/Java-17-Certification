package com.yuanyuanis.concurrency.uni.u1.b_multihilo.e_philosophers;

import java.util.logging.Logger;

public class Philosopher implements Runnable{

    private final Table table;
    private final String name;
    private final Fork right;
    private final Fork left;
    private boolean isLeftHanded;

    Logger log = Logger.getLogger(Philosopher.class.getName());

    public Philosopher(Table table, String name, Fork right, Fork left, boolean isLeftHanded) {
        this.table = table;
        this.name = name;
        this.right = right;
        this.left = left;
        this.isLeftHanded = isLeftHanded;
    }

    @Override
    public void run() {

        while(true) {
            try {
                think();
                eat();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void think() throws InterruptedException {
        // think for a while ...
        var timeThinking = table.getTime();
        log.info("%s is thinking for ... : %dms".formatted(name, timeThinking));
        Thread.sleep(timeThinking);
    }

    private void eat() throws InterruptedException {
        takeForks();
        // spend time eating ...
        var timeEating = table.getTime();
        log.info("%s is enyoing eat for ... : %dms".formatted(name, timeEating));
        Thread.sleep(timeEating);

        dropForks();

    }

    private void dropForks() {
        if(isLeftHanded){
            left.drop();
            right.drop();
        }else{
            right.drop();
            left.drop();
        }
    }

    private void takeForks() {
        if(isLeftHanded){
            left.take();
            right.take();
        }else{
            right.take();
            left.take();
        }
    }
}
