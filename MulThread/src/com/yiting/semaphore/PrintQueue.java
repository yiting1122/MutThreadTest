package com.yiting.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 打印机作业 一次只容许一个线程进行打印
 * @author yiting
 *
 */
public class PrintQueue {
	private static final int SIZE=3;
	Semaphore semaphore;
	boolean[] freePrinters;
	ReentrantLock lock;
	public PrintQueue(){
		semaphore=new Semaphore(SIZE+3);
		freePrinters=new boolean[SIZE];
		for(int i=0;i<freePrinters.length;i++)
			freePrinters[i]=true;
		lock=new ReentrantLock();
	}
	
	
	public void print(Object document){
		try {
			semaphore.acquire();
			int print=getPrint();
			System.out.println(Thread.currentThread().getName()+" print:"+print);
			Thread.sleep((long)Math.random()*10);
			freePrinters[print]=true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			semaphore.release();
		}
		
	}
	
	public int getPrint(){
		lock.lock();
		int ret=-1;
		try{
			for(int i=0;i<freePrinters.length;i++){
				if(freePrinters[i]==true){
					freePrinters[i]=false;
					ret=i;
					break;
				}
				if(i==freePrinters.length-1){
					i=0;
				}
			}
			return ret;
		}finally{
			lock.unlock();
		}
	}
	
	
}
