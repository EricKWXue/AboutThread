package com.eric.example.proandcust;

public class Customer2 implements Runnable{
	
	private Tmall2 tmall2;
	
	public Customer2(Tmall2 tmall2) {
		this.tmall2 = tmall2;
	}
	
	@Override
	public void run() {
		while (true) {
			tmall2.saleItem();			
		}
	}
}
