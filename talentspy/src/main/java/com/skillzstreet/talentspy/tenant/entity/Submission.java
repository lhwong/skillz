package com.skillzstreet.talentspy.tenant.entity;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.skillzstreet.talentspy.util.StringListDeserializer;

public class Submission {
	
	
	//@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@JsonDeserialize(using = StringListDeserializer.class)
	private List<String> answer;

	public List<String> getAnswer() {
		return answer;
	}

	public void setAnswer(List<String> answer) {
		this.answer = answer;
	}
	
	
	

}
