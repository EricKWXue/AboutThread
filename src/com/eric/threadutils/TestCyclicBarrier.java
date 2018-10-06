package com.eric.threadutils;

import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {
	
	public void goMeeting(CyclicBarrier cb){
		System.out.println(Thread.currentThread().getName()+"到达会议室");
		
		try {
			cb.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName()+"开始发言");
	}
	
	public static void main(String[] args) {
		TestCyclicBarrier tcb = new TestCyclicBarrier();
		
		CyclicBarrier cb = new CyclicBarrier(10, new Runnable() {			
			@Override
			public void run() {
				System.out.println("开始开会");
			}
		});
		
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					tcb.goMeeting(cb);
				}
			}).start();
		}
	}
}
