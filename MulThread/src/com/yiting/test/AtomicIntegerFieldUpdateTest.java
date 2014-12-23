package com.yiting.test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import org.junit.Test;

public class AtomicIntegerFieldUpdateTest {
	
	class Data{
		public volatile int value1;
	    volatile int value2;
		protected volatile int value3;
		private volatile int value4;
	}
	
	AtomicIntegerFieldUpdater<Data> getFieldUpdater(String field){
		return AtomicIntegerFieldUpdater.newUpdater(Data.class, field);
	}
	
	@Test
	public void testFieldUpdater(){
		Data data=new Data();
		AtomicIntegerFieldUpdateTest test=new AtomicIntegerFieldUpdateTest();
		
		System.out.println("field1="+test.getFieldUpdater("value1").addAndGet(data, 2));
		System.out.println("field2="+test.getFieldUpdater("value2").compareAndSet(data, 2, 4));
		
		System.out.println("field3="+test.getFieldUpdater("value3").decrementAndGet(data));
		System.out.println("field41="+test.getFieldUpdater("value4").compareAndSet(data, 3, 4));
	}

}
