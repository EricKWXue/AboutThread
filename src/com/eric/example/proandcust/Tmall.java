package com.eric.example.proandcust;

public class Tmall {
	
	private int itemNum = 0;
	
	private final int MAX_COUNT = 10;
	
	/**
	 * ������Ʒ
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
		System.out.println(Thread.currentThread().getName()+"������Ʒ����ǰ��������Ϊ"+itemNum);
		notifyAll();
	}
	
	/**
	 * ������Ʒ
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
		System.out.println(Thread.currentThread().getName()+"������Ʒ����ǰ��������Ϊ"+itemNum);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		notifyAll();
	}
}
