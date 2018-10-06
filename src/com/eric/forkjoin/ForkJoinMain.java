package com.eric.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinMain {
	
	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool(3);
		
		List<Integer> list = new ArrayList<>();
		for (int i=1; i<=100; i++) {
			list.add(i);
		}
		
		ForkJoinTest test = new ForkJoinTest(list, 2);
		
		pool.invoke(test);
		
		System.out.println(test.getSum());
		
		pool.shutdown();
	}
}
