package com.eric.threadutils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class TestCountDown {
	
	private int[] lineTotal;
	
	public TestCountDown(int lineNum){
		lineTotal = new int[lineNum];
	}
	
	public void calc(int lineNum, String line, CountDownLatch latch){
		int total = 0;
		String[] nums = line.split(",");
		for (String num : nums) {
			total+=Integer.parseInt(num);
		}
		lineTotal[lineNum] = total;		
		System.out.println(Thread.currentThread().getName()+"计算"+line+"，总和："+ total);
		
		latch.countDown();
	}
	
	public void sum(){
		int total = 0;
		for (int num : lineTotal) {
			total += num;
		}
		System.out.println("计算总和:"+total);
	}
	
	public static void main(String[] args) {
		List<String> contents = readFile();
		TestCountDown ts = new TestCountDown(contents.size());		
		//CountDownLatch
		CountDownLatch latch = new CountDownLatch(contents.size());
		
		for (int i = 0; i<contents.size(); i++) {
			
			final int j = i;			
			new Thread(new Runnable() {								
				@Override
				public void run() {
					ts.calc(j, contents.get(j), latch);
				}
			}).start();
		}
		
		try {
			//等待
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ts.sum();	
	}

	private static List<String> readFile() {
		List<String> content =  new ArrayList<>();
		String line = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("E:/javadb/mythread/resource/countdown.txt"));
			while((line = br.readLine()) != null){								
				content.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return content;
	}
}
