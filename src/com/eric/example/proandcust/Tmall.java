package com.eric.example.proandcust;

public class Tmall {
	
	private int itemNum = 0;
	
	private final int MAX_COUNT = 10;
	
	/**
	 * 生产商品
	 * @return
	 * @throws InterruptedException 
	 */
	public synchronized void productItem(){
		while(itemNum >= MAX_COUNT){
			try {
				wait();
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
		notifyAll();
	}
	
	/**
	 * 消费商品
	 * @return
	 */
	public synchronized void saleItem(){
		while(itemNum <= 0){
			try {
				wait();
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
		notifyAll();
	}
}
