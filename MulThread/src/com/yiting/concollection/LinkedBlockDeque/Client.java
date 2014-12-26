package com.yiting.concollection.LinkedBlockDeque;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Client implements Runnable {

	private LinkedBlockingDeque<String> requestList;

	public Client(LinkedBlockingDeque<String> requestList) {
		this.requestList = requestList;
	}

	@Override
	public void run() {
		for(int i=0;i<3;i++){
			for(int j=0;j<5;j++){
				StringBuilder request=new StringBuilder();
				request.append(i);
				request.append(":");
				request.append(j);
				try{
					requestList.put(request.toString());
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("client"+request.toString()+" Date:"+new Date());
				
			}
			
			try{
				TimeUnit.SECONDS.sleep(2);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Client End");
			
		}
	}

}
