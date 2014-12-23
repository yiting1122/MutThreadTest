package com.yiting.test;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class AtomicIntegerTest {
	public static final int THREAD_SIZE=20;
	@Test
	public void testAll(){
		final AtomicInteger value=new AtomicInteger(10);
		
		Thread[] t=new Thread[THREAD_SIZE];
		
		for(int i=0;i<THREAD_SIZE;i++){
			t[i]=new Thread(){
				@Override
				public void run() {
					int temp=value.incrementAndGet();
					System.out.println("thread "+Thread.currentThread()+" value is :"+temp);
				}
				
			};
		}
		
		for(Thread tt:t){
			tt.start();
		}
		
		for(Thread tt:t){
			try {
				tt.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("the value is :"+value.get());
		System.out.println("the main thread is over");
		
	}

}
