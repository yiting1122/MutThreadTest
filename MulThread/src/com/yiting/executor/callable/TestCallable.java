package com.yiting.executor.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TestCallable {
	@Test
	public void test() {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(2);
		List<Future<Integer>> resultList = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			Integer num = random.nextInt(10);
			FactorialCalculator calculator = new FactorialCalculator(num);
			Future<Integer> result = executor.submit(calculator);
			resultList.add(result);
		}
//		do { //主线程自旋等待完成
//			System.out.println("Number of Completed:"
//					+ executor.getCompletedTaskCount());
//			for (int i = 0; i < resultList.size(); i++) {
//				Future<Integer> ret = resultList.get(i);
//				System.out.println("task:" + i + "  " + ret.isDone());
//			}
//			try {
//				TimeUnit.MILLISECONDS.sleep(50);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		} while (executor.getCompletedTaskCount() < resultList.size());

		System.out.println("=================result================");
		for(int i=0;i<resultList.size();i++){
			Future<Integer> ret=resultList.get(i);
			Integer number=null;
			try {
				System.out.println(ret.isDone());
				number=ret.get();
				System.out.println(ret.isDone());
				System.out.println(i+" result "+number);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
}
