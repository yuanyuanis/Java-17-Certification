package com.yuanyuanis.concurrency.uni.u1.b_multihilo.e_philosophers;

import java.util.concurrent.locks.ReentrantLock;

public class Fork {

    private ReentrantLock lock = new ReentrantLock();

    public void take(){
        lock.lock();
    }
    public void drop(){
        if(lock.isHeldByCurrentThread()){
            lock.unlock();
        }
    }


}
