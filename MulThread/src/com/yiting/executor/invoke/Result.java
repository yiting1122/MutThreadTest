package com.yiting.executor.invoke;

public class Result {
	public String name;
	public String value;

	public Result(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "name: "+this.name+"value:"+this.value;
	}

	
}
