package com.eric.example.proandcust;

public class Customer implements Runnable{
	
	private Tmall tmall;
	
	public Customer(Tmall tmall) {
		this.tmall = tmall;
	}
	
	@Override
	public void run() {
		while (true) {
			tmall.saleItem();			
		}
	}
}
