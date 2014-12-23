package com.yiting.lock;

import org.junit.Test;

public class TestSome {
	@Test
	public void testFun(){
		int a=3;
		while(a!=3&&(a=4)!=2){
			a=5;
		}
		System.out.println(a);
	}
}
