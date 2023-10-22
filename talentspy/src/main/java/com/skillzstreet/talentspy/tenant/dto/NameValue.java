package com.skillzstreet.talentspy.tenant.dto;




public class NameValue {
	private String name;
	private Object value;

	public NameValue(String name, Object value) {
		this.name = name;
		this.value = value;
	}
	
	public NameValue(Integer num, Object value) {
		this.name = num.toString();
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	
	
	
	
}