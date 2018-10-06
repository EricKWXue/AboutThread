package com.eric.mylock;

public class ThreadTestEricLock {
	private int value = 0;
	private EricLock lock = new EricLock();
	
	public int getValue(){
		lock.lock();
		int tmp;
		try {
			Thread.sleep(300);
			tmp = value++;
		} catch (InterruptedException e) {
			throw new RuntimeException(e.getMessage());
		}finally {
			lock.unlock();
		}		
		return tmp;
	}
	
	public static void main(String[] args) {
		ThreadTestEricLock test = new ThreadTestEricLock();
		new Thread(new Runnable() {
			public void run() {
				while(true){
					System.out.println(Thread.currentThread().getName()+"..."+test.getValue());
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				while(true){
					System.out.println(Thread.currentThread().getName()+"..."+test.getValue());
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				while(true){
					System.out.println(Thread.currentThread().getName()+"..."+test.getValue());
				}
			}
		}).start();
	}
}
