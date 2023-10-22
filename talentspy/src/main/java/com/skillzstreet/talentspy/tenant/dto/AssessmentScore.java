package com.skillzstreet.talentspy.tenant.dto;

import java.util.UUID;

public class AssessmentScore {
	
	private UUID id;
	
	private String title;
	
	
	private UUID skillId;
	
	private Double score;
	
	
	
	public AssessmentScore(UUID id, String title, UUID skillId, double score) {
		this.id = id;
		this.title = title;
		this.skillId = skillId;
		this.score = score;
	}



	public UUID getId() {
		return id;
	}



	public void setId(UUID id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}
	
	
	

	public UUID getSkillId() {
		return skillId;
	}



	public void setSkillId(UUID skillId) {
		this.skillId = skillId;
	}



	public Double getScore() {
		return score;
	}



	public void setScore(Double score) {
		this.score = score;
	}
	
	
	public String getSubCategory() {
		if (title.contains("Pre")) {
			return "Pre";
		} else if (title.contains("Post")) {
			return "Post";
		} else {
			return "Others";
		}
	}



}
