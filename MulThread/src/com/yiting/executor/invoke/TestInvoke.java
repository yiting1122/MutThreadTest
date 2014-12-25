package com.yiting.executor.invoke;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Before;
import org.junit.Test;

public class TestInvoke {
	public ThreadPoolExecutor executor;
	
	@Before
	public void init(){
		executor=(ThreadPoolExecutor) Executors.newFixedThreadPool(2);
	}
	
	@Test
	public void testInovke(){
		List<Task> tasks=new ArrayList<>();
		for(int i=0;i<100;i++){
			Task t=new Task(String.valueOf(i));
			tasks.add(t);
		}
		
		List<Future<Result>> retList=new ArrayList<>();
		try {
			retList=executor.invokeAll(tasks);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		executor.shutdown();
		for(Future<Result> ret:retList){
			try {
				System.out.println(ret.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Main thread is end");
	}
}
