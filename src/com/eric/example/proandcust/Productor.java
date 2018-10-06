package com.eric.example.proandcust;

public class Productor implements Runnable{
	
	private Tmall tmall;
	
	public Productor(Tmall tmall) {
		this.tmall = tmall;
	}

	@Override
	public void run() {
		while (true) {
			tmall.productItem();
		}
	}	
}
