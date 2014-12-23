package com.yiting.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AtomicIntegerByLock {
	private int value;
	private Lock lock = new ReentrantLock();

	public AtomicIntegerByLock(int value) {
		this.value = value;
	}

	public int get() {
		return this.value;
	}

	public void set(int newValue) {
		lock.lock();
		try {
			value = newValue;
		} finally {
			lock.unlock();
		}
	}

	public int getAndSet(int newValue) {
		lock.lock();
		try {
			int oldValue = this.value;
			this.value = newValue;
			return oldValue;
		} finally {
			lock.unlock();
		}

	}

	public int getAndIncrement() {
		lock.lock();
		try {
			int oldValue = this.value;
			this.value++;
			return oldValue;
		} finally {
			lock.unlock();
		}
	}

	public int getAndDecrement() {
		lock.lock();
		try {
			int oldValue = this.value;
			this.value--;
			return oldValue;
		} finally {
			lock.unlock();
		}
	}

	public boolean compareAndSet(int expect, int newValue) {
		lock.lock();
		try {
			if (expect == this.value) {
				this.value = newValue;
				return true;
			}
			return false;
		} finally {
			lock.unlock();
		}
	}

}
