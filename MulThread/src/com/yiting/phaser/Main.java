package com.yiting.phaser;

import java.util.concurrent.Phaser;

import org.junit.Test;

public class Main {
	@Test
	public void testFileSearch(){
		Phaser phaser=new Phaser(3);
		FileSearch systemsSearch=new FileSearch("C:\\Windows", "log", phaser);
		FileSearch appsSearch=new FileSearch("c:\\Program Files", "log", phaser);
		FileSearch documentSearch=new FileSearch("c:\\Document And Settings", "log", phaser);
	    
		Thread systemThread=new Thread(systemsSearch,"system");
		systemThread.start();
		
		Thread appsThread=new Thread(appsSearch,"appsThread");
		appsThread.start();
		
		Thread documentThread=new Thread(documentSearch,"documentThread");
		documentThread.start();
		
		try {
			System.out.println("0");
			documentThread.join();
			System.out.println("1");
			appsThread.join();
			System.out.println("2");
			systemThread.join();
			System.out.println("3");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("terminated :"+phaser.isTerminated());
		
	}
}
