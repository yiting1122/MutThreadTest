package com.yiting.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Producer implements Runnable {

	private List<String> buffer;
	private final Exchanger<List<String>> exchanger;

	public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		int cycle = 1;
		for (int i = 0; i < 10; i++) {
			System.out.println("Producer : cycle " + cycle);
			for (int j = 0; j < 10; j++) {
				String message = "event" + ((i * 10) + j);
				buffer.add(message);
			}

			try {
				System.out.println("wait buffer from consumer by producer");
				buffer = exchanger.exchange(buffer);
				System.out.println("get buffer from consumer by producer");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Produce :" + buffer.size());
			cycle++;
		}
	}

}
