package com.skillzstreet.talentspy.tenant.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Answer {
	
	
	//@JsonDeserialize(using = StringListDeserializer.class)
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<Integer> answer;

	public List<Integer> getAnswer() {
		return answer;
	}

	
	public void setAnswer(List<Integer> answer) {
		this.answer = answer;
	}

	

	
	

	

	
	
	
	
	

}
