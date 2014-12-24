package com.yiting.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Consumer implements Runnable {

	private List<String> buffer;
	private final Exchanger<List<String>> exchanger;

	public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
		super();
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		int cycle = 1;
		for (int i = 0; i < 10; i++) {
			System.out.println("comsumer: cycle" + cycle);
			;
			try {
				System.out.println("wait buffer from Producer by consumer");
				buffer = exchanger.exchange(buffer);
				System.out.println("get buffer from Producer by consumer");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Consumer:" + buffer.size());
			for (int j = 0; j < 10; j++) {
				String message = buffer.get(0);
				System.out.println("consumer: " + message);
				buffer.remove(0);
			}
			cycle++;
		}
	}

}
