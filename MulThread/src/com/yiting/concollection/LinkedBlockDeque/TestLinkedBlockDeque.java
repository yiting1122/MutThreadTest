package com.yiting.concollection.LinkedBlockDeque;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TestLinkedBlockDeque {
	
	@Test
	public void testLinkedBlockDeque(){
		LinkedBlockingDeque<String> list=new LinkedBlockingDeque<>();
		Client client=new Client(list);
		Thread thread=new Thread(client);
		thread.start();
		
		
		for(int i=0;i<5;i++){
			for(int j=0;j<3;j++){
				try {
					list.take();
					System.out.println("take one string the size is:"+list.size());
					TimeUnit.MILLISECONDS.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				
			}
		}
		
	}
	
	
}
