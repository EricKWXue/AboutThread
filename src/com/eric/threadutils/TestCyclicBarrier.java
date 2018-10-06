package com.eric.threadutils;

import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {
	
	public void goMeeting(CyclicBarrier cb){
		System.out.println(Thread.currentThread().getName()+"���������");
		
		try {
			cb.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName()+"��ʼ����");
	}
	
	public static void main(String[] args) {
		TestCyclicBarrier tcb = new TestCyclicBarrier();
		
		CyclicBarrier cb = new CyclicBarrier(10, new Runnable() {			
			@Override
			public void run() {
				System.out.println("��ʼ����");
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
