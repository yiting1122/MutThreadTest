package com.yiting.queue;

import java.util.Random;

import org.junit.Test;

public class QueueTest {

	public static final int CONSUMER_SIZE = 10;
	public static final int PRODUCER_SIZE = 10;

	@Test
	public void testConsumer() {

		final ProductQueue<String> queue = new ProductQueue<String>(2);

		Thread[] consumers = new Thread[CONSUMER_SIZE];
		Thread[] producers = new Thread[PRODUCER_SIZE];
		for (int i = 0; i < CONSUMER_SIZE; i++) {
			consumers[i] = new Thread() {
				@Override
				public void run() {
					Random random = new Random();
					try {
						queue.put(Thread.currentThread()+"string is" + random.nextInt());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}

		for (int i = 0; i < PRODUCER_SIZE; i++) {
			producers[i] = new Thread() {
				@Override
				public void run() {
					try {
						System.out.println(Thread.currentThread()+"take "+queue.take());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}
		
		
		
		for(Thread c:consumers){
			c.start();
		}
		
		for(Thread p:producers){
			p.start();
		}
		
		
		for(Thread c:consumers){
			try {
				c.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(Thread p:producers){
			try {
				p.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
