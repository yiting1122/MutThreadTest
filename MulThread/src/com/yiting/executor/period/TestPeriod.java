package com.yiting.executor.period;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

public class TestPeriod {

	private ScheduledThreadPoolExecutor executor;
	@Before
	public void init(){
		executor=(ScheduledThreadPoolExecutor)Executors.newScheduledThreadPool(1);
	}
	
	@Test
	public void testPeriod(){
		System.out.println("Main started at :"+new Date());
		Task t=new Task("task");
		ScheduledFuture<?> result=executor.scheduleAtFixedRate(t, 1, 2, TimeUnit.SECONDS);
		for(int i=0;i<10;i++){
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
		executor.shutdown();
		System.out.println("Main : Finished");
	}
}
