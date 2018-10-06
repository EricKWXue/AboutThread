package com.eric.example.proandcust;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tmall2 {
	
	private int itemNum = 0;
	
	private final int MAX_COUNT = 10;
	
	private Lock lock = new ReentrantLock();
	
	private Condition p = lock.newCondition();
	private Condition c = lock.newCondition();
	
	/**
	 * ������Ʒ
	 * @return
	 * @throws InterruptedException 
	 */
	public void productItem(){
		lock.lock();
		while(itemNum >= MAX_COUNT){
			try {
				p.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		itemNum++;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"������Ʒ����ǰ��������Ϊ"+itemNum);
		c.signal();
		lock.unlock();
	}
	
	/**
	 * ������Ʒ
	 * @return
	 */
	public void saleItem(){
		lock.lock();
		while(itemNum <= 0){
			try {
				c.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		itemNum--;
		System.out.println(Thread.currentThread().getName()+"������Ʒ����ǰ��������Ϊ"+itemNum);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		p.signal();
		lock.unlock();
	}
}
