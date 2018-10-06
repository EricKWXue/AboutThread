package com.eric.example.proandcust;

public class TestMain {
	
	public static void main(String[] args) {
		
		Tmall tmall = new Tmall();
		
		Productor p1 = new Productor(tmall);
		Productor p2 = new Productor(tmall);
		Productor p3 = new Productor(tmall);
		Productor p4 = new Productor(tmall);
		Productor p5 = new Productor(tmall);
		new Thread(p1).start();
		new Thread(p2).start();
		new Thread(p3).start();
		new Thread(p4).start();
		new Thread(p5).start();
		
		Customer c1 = new Customer(tmall);
		Customer c2 = new Customer(tmall);
		Customer c3 = new Customer(tmall);
		Customer c4 = new Customer(tmall);
		Customer c5 = new Customer(tmall);
		new Thread(c1).start();
		new Thread(c2).start();
		new Thread(c3).start();
		new Thread(c4).start();
		new Thread(c5).start();
		
	}
}
