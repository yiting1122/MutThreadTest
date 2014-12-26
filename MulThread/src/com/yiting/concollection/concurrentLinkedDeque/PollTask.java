package com.yiting.concollection.concurrentLinkedDeque;

import java.util.concurrent.ConcurrentLinkedDeque;

public class PollTask implements Runnable{
	private ConcurrentLinkedDeque<String> list;
	
	public PollTask(ConcurrentLinkedDeque<String> list){
		this.list=list;
	}
	
	@Override
	public void run() {
		for(int i=0;i<5000;i++){
			
			list.pollFirst();
			list.pollLast();
			//System.out.println(list.size());
		}
	}

}
