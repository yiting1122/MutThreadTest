package com.yiting.executor.invoke;

import java.util.Random;
import java.util.concurrent.Callable;

public class Task implements Callable<Result>{
	private String name;
	public Task(String name) {
		this.name = name;
	}


	@Override
	public Result call() throws Exception {
		int i=0;
		for(;i<(new Random()).nextInt(50);i++){
			i*=i;
		}
		System.out.println("thread :"+Thread.currentThread());
		return new Result(this.name, String.valueOf(i));
	}

}
