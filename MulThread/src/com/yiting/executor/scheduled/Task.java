package com.yiting.executor.scheduled;

import java.util.Date;
import java.util.concurrent.Callable;

public class Task implements Callable<String> {

	private String name;

	public Task(String name) {
		this.name = name;
	}

	@Override
	public String call() throws Exception {
		System.out.println(name + " started at :" + new Date() + ":thread:"
				+ Thread.currentThread());
		return "hello,world";

	}
}
