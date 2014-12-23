package com.yiting.lock;

import org.junit.Test;

public class LockTest {
	public static final int THREAD_SIZE = 10;
	public static final int LOOP_COUNT = 100000;

	public static final void main(String[] args) {
		(new LockTest()).testSpeed();
	}

	@Test
	public void testSpeed() {
		System.out.println("the time from lock used :" + getTimeFromLock());

		System.out.println("the time from synchromize :"
				+ getTimefromSynchronize());
	}

	public long getTimeFromLock() {
		final AtomicIntegerByLock lockInteger = new AtomicIntegerByLock(1);
		Thread[] ts = new Thread[THREAD_SIZE];
		for (int i = 0; i < THREAD_SIZE; i++) {
			ts[i] = new Thread() {
				@Override
				public void run() {
					for (int j = 0; j < LOOP_COUNT; j++) {
//						System.out.println("thread :" + Thread.currentThread()
//								+ "lock:" + lockInteger.getAndIncrement());
						lockInteger.getAndIncrement();
					}
				}
			};
		}

		long start = System.currentTimeMillis();

		for (Thread t : ts) {
			t.start();
		}

		for (Thread t : ts) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("lock is over");
		return end - start;
	}

	public long getTimefromSynchronize() {
		final Mvalue value = new Mvalue(1);
		final Object lock = new Object();
		Thread[] ts = new Thread[THREAD_SIZE];
		for (int i = 0; i < THREAD_SIZE; i++) {
			ts[i] = new Thread() {
				@Override
				public void run() {

					for (int j = 0; j < LOOP_COUNT; j++) {
						synchronized (lock) {
//							System.out.println("thread :"
//									+ Thread.currentThread() + "syn"
//									+ value.value++);
							value.value++;
						}
					}
				}
			};
		}

		long start = System.currentTimeMillis();

		for (Thread t : ts) {
			t.start();
		}

		for (Thread t : ts) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		return end - start;
	}

	private class Mvalue {
		public int value;

		public Mvalue(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

	}

}
