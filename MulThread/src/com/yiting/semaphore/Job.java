package com.yiting.semaphore;

public class Job implements Runnable{
	
	private static final int MAX_SIZE=1000;
	private  PrintQueue queue;
	public Job(PrintQueue queue){
		this.queue=queue;
	}
	
	
	
	public static void main(String[] args){
		PrintQueue mqueue=new PrintQueue();
		Thread[] threads=new Thread[MAX_SIZE];
		
		for(int i=0;i<threads.length;i++){
			threads[i]=new Thread(new Job(mqueue), "thread"+i);
		}
		
		for(Thread t:threads){
			t.start();
		}
		
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"  job");
		queue.print(new Object());
	}
	
}
