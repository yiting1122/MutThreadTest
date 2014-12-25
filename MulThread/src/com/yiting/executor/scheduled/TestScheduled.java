package com.yiting.executor.scheduled;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



import org.junit.Before;
import org.junit.Test;

public class TestScheduled {
	
	public ScheduledThreadPoolExecutor executor;
	
	@Before
	public void init(){
		executor=(ScheduledThreadPoolExecutor)Executors.newScheduledThreadPool(3);
	}
	
	@Test
	public void testScheduled(){
		System.out.println("Main start at "+new Date());
		
		for(int i=0;i<10;i++){
			Task t=new Task("Task "+i);
			executor.schedule(t, i+1, TimeUnit.SECONDS);
		}
		
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Main :end at:" +new Date());
	}
	
}
