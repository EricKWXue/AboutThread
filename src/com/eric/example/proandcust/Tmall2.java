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
	 * 生产商品
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
		System.out.println(Thread.currentThread().getName()+"生产商品，当前数量增加为"+itemNum);
		c.signal();
		lock.unlock();
	}
	
	/**
	 * 消费商品
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
		System.out.println(Thread.currentThread().getName()+"消费商品，当前数量减少为"+itemNum);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		p.signal();
		lock.unlock();
	}
}
