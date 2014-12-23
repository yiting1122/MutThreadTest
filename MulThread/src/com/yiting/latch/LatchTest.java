package com.yiting.latch;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class LatchTest {
	@Test
	public void testLatch() {
		(new LatchTest()).doThing(10);
	}

	public void doThing(int threadTimes) {
		final CountDownLatch startLatch = new CountDownLatch(1);
		final CountDownLatch overLatch = new CountDownLatch(threadTimes);

		Thread[] threads = new Thread[threadTimes];

		for (int i = 0; i < threadTimes; i++) {
			threads[i] = new Thread() {
				@Override
				public void run() {
					try {
						startLatch.await();
						System.out.println(Thread.currentThread());
						overLatch.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}

		for (Thread t : threads) {
			t.start();
		}
		
		System.out.println("thread is start running task");
		startLatch.countDown();
		try {
			overLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("the main is over!");

	}
}
