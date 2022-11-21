package com.yuanyuanis.concurrency.ocp.a_threads;

import java.time.LocalDateTime;

public class ThreadsTest2_Sleep {
	
	public static void main(String ...args) {
		
		var job1 = new Thread(() -> {
			try {
				System.out.println("Thread["+Thread.currentThread().getName()+"] start: " + LocalDateTime.now());
				Thread.sleep(5_000);
				System.out.println("Thread["+Thread.currentThread().getName()+"] end: " + LocalDateTime.now());
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "job1");
		
		job1.start();
		
		
	}

}
