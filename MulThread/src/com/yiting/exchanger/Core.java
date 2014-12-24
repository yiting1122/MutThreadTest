package com.yiting.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

import org.junit.Test;

public class Core {
	@Test
	public void testExchanger(){
		List<String> buffer1=new ArrayList<>();
		List<String> buffer2=new ArrayList<>();
		Exchanger<List<String>>exchanger=new Exchanger<>();
		Producer producer=new Producer(buffer1, exchanger);
		Consumer consumer=new Consumer(buffer2, exchanger);
		Thread threadProducer=new Thread(producer);
		Thread threadConsumer=new Thread(consumer);
		threadProducer.start();
		threadConsumer.start();
		
		try {
			threadProducer.join();
			threadConsumer.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
