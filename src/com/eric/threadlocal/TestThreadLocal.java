package com.eric.threadlocal;

public class TestThreadLocal {
	private ThreadLocal<Integer> count = new ThreadLocal<Integer>(){
		@Override
		protected Integer initialValue() {
	        return 0;
	    }
	};
	
	public Integer getValue(){
		Integer value = count.get();
		value++;
		count.set(value);
		return value;
	}
	
	public static void main(String[] args) {
		TestThreadLocal local = new TestThreadLocal();
		new Thread(new Runnable() {
			public void run() {
				while(true){
					System.out.println(Thread.currentThread().getName()+" : "+local.getValue());
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				while(true){
					System.out.println(Thread.currentThread().getName()+" : "+local.getValue());
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				while(true){
					System.out.println(Thread.currentThread().getName()+" : "+local.getValue());
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
}
