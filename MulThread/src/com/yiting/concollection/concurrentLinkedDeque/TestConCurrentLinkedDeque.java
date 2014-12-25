package com.yiting.concollection.concurrentLinkedDeque;

import java.util.concurrent.ConcurrentLinkedDeque;

import org.junit.Test;

public class TestConCurrentLinkedDeque {
	@Test
	public void testDeque(){
		ConcurrentLinkedDeque<String> list=new ConcurrentLinkedDeque<>();
		Thread threads[]=new Thread[100];
		for(int i=0;i<threads.length;i++){
			AddTask task=new AddTask(list);
			threads[i]=new Thread(task);
			threads[i].start();
		}
		
		for(int i=0;i<threads.length;i++){
			try {
				threads[i].join();
			} catch (Exception e) {
				
			}
		}
		System.out.println("Size of list :"+list.size());
		for(int i=0;i<threads.length;i++){
			PollTask task=new PollTask(list);
			threads[i]=new Thread(task);
			threads[i].start();
		}
		
		for(int i=0;i<threads.length;i++){
			try {
				threads[i].join();
			} catch (Exception e) {
				
			}
		}
		
		System.out.println("Size of list :"+list.size());
	}
	
}
