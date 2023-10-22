package com.skillzstreet.talentspy.tenant.entity;



import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "learning_path")
//@EntityListeners(AuditingEntityListener.class)
public class LearningPath {
	

    
    @Id
	private UUID id;
	
	private String name;
	
	private Integer path;
	
	private Integer sequence;
	
	//@CreatedDate
	//private Date created;
	
	//@LastModifiedDate
	//private Date modified;
	
	//Todo: using View
	@JsonIgnore
	@OneToMany(mappedBy = "learningPath", fetch = FetchType.LAZY)
    Set<Enrollment> enrollments;
	

	

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

	public Integer getPath() {
		return path;
	}

	public void setPath(Integer path) {
		this.path = path;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Set<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(Set<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
	
	
	/*public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}*/

	
	
	


}
