package com.yiting.test;

public class B implements A{
	
	
	A getA(){
		return new B();
	}

}
