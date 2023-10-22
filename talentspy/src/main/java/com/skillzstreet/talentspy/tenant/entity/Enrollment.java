package com.skillzstreet.talentspy.tenant.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Enrollment {
	
	@Id
    UUID id;
 
    @ManyToOne
    @JoinColumn(name = "talentId")
    Talent talent;
 
    @ManyToOne
    @JoinColumn(name = "learningPathId")
    LearningPath learningPath;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Talent getTalent() {
		return talent;
	}

	public void setTalent(Talent talent) {
		this.talent = talent;
	}

	public LearningPath getLearningPath() {
		return learningPath;
	}

	public void setLearningPath(LearningPath learningPath) {
		this.learningPath = learningPath;
	}
    
    
    

}
