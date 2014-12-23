package com.yiting.queue;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProductQueue<T> {
	private final T[] items;
	private final Lock lock = new ReentrantLock();
	private Condition fullCondition = lock.newCondition();
	private Condition emptyCondition = lock.newCondition();
	
	private int head, tail, count;

	public ProductQueue(int maxSize) {
		this.items = (T[]) new Object[maxSize];
	}

	public ProductQueue() {
		this(10);
	}

	public void put(T item) throws InterruptedException {
		lock.lock();
		try {
			while (count == getCapacity()) {
				fullCondition.await();
			}
			items[tail] = item;
			if (++tail == getCapacity()) {
				tail = 0;
			}
			count++;
			emptyCondition.signalAll();

		} finally {
			lock.unlock();
		}

	}

	public T take() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0) {
				emptyCondition.await();
			}
			T ret = items[head];
			items[head] = null;
			if (++head == getCapacity()) {
				head = 0;
			}
			count--;
			fullCondition.signalAll();
			return ret;
		} finally {
			lock.unlock();
		}
	}

	public int size() {
		lock.lock();
		try {
			return count;
		} finally {
			lock.unlock();
		}
	}

	public int getCapacity() {
		return items.length;
	}

}
