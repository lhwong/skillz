package com.skillzstreet.talentspy.tenant.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "profiles")
public class Skill {

	@JsonView(Views.Skill.class)
	@Id
	private UUID id;
	
	@JsonView(Views.Skill.class)
	private String name;
	
	@JsonView(Views.Skill.class)
	private String description;
	
	@JsonView(Views.Skill.class)
	private Date created;
	

	@JsonView(Views.Goal.class)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	  @JoinTable
	  (
	      name="profiles_skills",
	      joinColumns={ @JoinColumn(name="PROFILE_ID", referencedColumnName="ID") },
	      inverseJoinColumns={ @JoinColumn(name="SKILL_ID", referencedColumnName="ID", unique=true) }
	  )
	private List<Goal> goals;
	
	
	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Goal> getGoals() {
		return goals;
	}

	public void setGoals(List<Goal> goals) {
		this.goals = goals;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	
	
}
