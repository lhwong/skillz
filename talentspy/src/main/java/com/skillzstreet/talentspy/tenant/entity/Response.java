package com.skillzstreet.talentspy.tenant.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.annotation.JsonView;
import com.skillzstreet.talentspy.tenant.dto.AssessmentScore;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

@Entity
@Table(name = "survey_responses")
@TypeDef(typeClass = JsonStringType.class, name = "json")



@SqlResultSetMappings({

	@SqlResultSetMapping(
	    name="score",
	    classes={
	        @ConstructorResult(
	            targetClass=AssessmentScore.class,
	            columns={
	            	@ColumnResult(name="id", type=UUID.class),
	            	@ColumnResult(name="title", type=String.class),
	            	@ColumnResult(name="skillId", type=UUID.class),
	            	@ColumnResult(name="score", type=Double.class)
	                
	            }
	        )
	    })


})

@NamedNativeQueries({
	
	
	// @formatter:off
	@NamedNativeQuery(name="Response.findScore", 
	query = "select " + 
			"	a.id as id, a.title as title, a.profile_id as skillId, " + 
			"   processed_data->'profile'->>'pctScore' as score " + 
			"from " + 
			"   survey_responses r inner join surveys a on r.survey_id = a.id " +
			"order by r.id", resultSetMapping="score")
	// @formatter:on
	
})


public class Response {
	
	@JsonView(Views.Skill.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Assessment assessment;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="talent_id")
	private Talent talent;
	
	@JsonView(Views.Skill.class)
	@Type(type = "json")
    @Column(name="processed_data", columnDefinition = "json")
    private String processed;
	
	
	@Type(type = "json")
    @Column(name="form_schema", columnDefinition = "json")
    private String questions;
	
	@Type(type = "json")
    @Column(name="answer_schema", columnDefinition = "json")
    private String answers;
	
	@Type(type = "json")
    @Column(name="submission_data", columnDefinition = "json")
    private String values;
	
	
	private Integer status;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	

	public Assessment getAssessment() {
		return assessment;
	}


	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}
	
	
	


	public Talent getTalent() {
		return talent;
	}


	public void setTalent(Talent talent) {
		this.talent = talent;
	}


	


	public String getQuestions() {
		return questions;
	}


	public void setQuestions(String questions) {
		this.questions = questions;
	}


	public String getAnswers() {
		return answers;
	}


	public void setAnswers(String answers) {
		this.answers = answers;
	}


	public String getValues() {
		return values;
	}


	public void setValues(String values) {
		this.values = values;
	}


	public String getProcessed() {
		return processed;
	}


	public void setProcessed(String processed) {
		this.processed = processed;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	
	
	
	

}
