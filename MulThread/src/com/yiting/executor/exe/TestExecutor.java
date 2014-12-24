package com.yiting.executor.exe;

import org.junit.Test;

public class TestExecutor {
	@Test
	public void testExecutor(){
		Server server=new Server();
		for(int i=0;i<100;i++){
			Task task=new Task("MTask "+i);
			server.executeTask(task);
		}
		System.out.println("server running");
		server.endServer();
		System.out.println("server shutDown");
	}
}
