package com.yiting.executor.exe;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
	private ThreadPoolExecutor executor;

	public Server() {
		executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
	}
	
	public void executeTask(Task task){
		System.out.println("server: A new task has arrived");
		executor.execute(task);
		System.out.println("server :pool size: "+executor.getPoolSize());
		System.out.println("server: Active count:"+executor.getActiveCount());
		System.out.println("server: Completed Tasks:"+executor.getCompletedTaskCount());
	}
	
	public void endServer(){
		executor.shutdown();
//		try {
//			executor.awaitTermination(5L, TimeUnit.SECONDS);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	
	
	
}
