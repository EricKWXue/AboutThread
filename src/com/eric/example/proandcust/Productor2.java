package com.eric.example.proandcust;

public class Productor2 implements Runnable{
	
	private Tmall2 tmall2;
	
	public Productor2(Tmall2 tmall2) {
		this.tmall2 = tmall2;
	}

	@Override
	public void run() {
		while (true) {
			tmall2.productItem();
		}
	}	
}
