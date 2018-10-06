package com.eric.example.proandcust;

public class TestMain2 {
	
	public static void main(String[] args) {
		
		Tmall2 tmall2 = new Tmall2();
		
		Productor2 p1 = new Productor2(tmall2);
		Productor2 p2 = new Productor2(tmall2);
		Productor2 p3 = new Productor2(tmall2);
		Productor2 p4 = new Productor2(tmall2);
		Productor2 p5 = new Productor2(tmall2);
		new Thread(p1).start();
		new Thread(p2).start();
		new Thread(p3).start();
		new Thread(p4).start();
		new Thread(p5).start();
		
		Customer2 c1 = new Customer2(tmall2);
		Customer2 c2 = new Customer2(tmall2);
		Customer2 c3 = new Customer2(tmall2);
		Customer2 c4 = new Customer2(tmall2);
		Customer2 c5 = new Customer2(tmall2);
		new Thread(c1).start();
		new Thread(c2).start();
		new Thread(c3).start();
		new Thread(c4).start();
		new Thread(c5).start();
		
	}
}
