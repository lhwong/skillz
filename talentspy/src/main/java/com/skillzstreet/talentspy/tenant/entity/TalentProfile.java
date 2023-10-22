package com.skillzstreet.talentspy.tenant.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * User entity to represent a {@link TalentProfile} of the system.
 * 
 * The JPA definitions of {@link TalentProfile} and {@link Role} will cause the following
 * 3 tables to be created:
 * <ul>
 * <li>user</li>
 * <li>role</li>
 * <li>user_roles</li>
 * </ul>
 * 
 */
@Entity
@Table(name = "talent_profiles")
public class TalentProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @ManyToOne(optional = false)
    @JoinColumn(name="department_id")
    private Department department;
    
    @Column(name = "job_title")
    private String jobTitle;
    
    @OneToOne
    @JoinColumn(name="TALENT_ID")
    private Talent talent;
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
    

	
}
