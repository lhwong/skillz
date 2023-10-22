package com.skillzstreet.talentspy.tenant.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name = "surveys")
public class Assessment implements Serializable {
	

	private static final long serialVersionUID = 8585067989942429385L;

	@JsonView(Views.Skill.class)
	@Id
	private UUID id;
 
	@JsonView(Views.Skill.class)
    private String title;
    
	@JsonView(Views.Skill.class)
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROFILE_ID")
    private Skill skill;
    
	@JsonView(Views.Skill.class)
    @OneToMany(mappedBy="assessment")
    private List<Response> responses;
    
    private Date created;
    

	

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

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}


	@Override
    public boolean equals(Object o) {
        if (this == o) 
            return true;
        if (o == null || getClass() != o.getClass()) 
            return false;
            
        Assessment that = (Assessment) o;
        
        return that.id.equals(id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    

}
