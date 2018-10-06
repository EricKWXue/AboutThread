package com.eric.forkjoin;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class ForkJoinTest extends RecursiveAction {
	
	private List<Integer> list;
	
	private int maxSize;
	
	private static int sum;
	
	public ForkJoinTest(List<Integer> list, int maxSize){
		this.list = list;
		this.maxSize = maxSize;
	}

	@Override
	protected void compute() {
		if(list.size() < maxSize){
			for (Integer integer : list) {
				sum += integer;
			}
		}else{
			int midIndex = list.size()/2;
			List<Integer> leftList = list.subList(0, midIndex);
			List<Integer> rightList = list.subList(midIndex, list.size());
			
			ForkJoinTest left = new ForkJoinTest(leftList, maxSize);
			ForkJoinTest right = new ForkJoinTest(rightList, maxSize);
			
			invokeAll(left, right);
			
			left.join();
			right.join();
		}
	}

	public static Integer getSum() {
		return sum;
	}
	
}
