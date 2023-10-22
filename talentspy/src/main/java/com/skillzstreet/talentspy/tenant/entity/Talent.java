package com.skillzstreet.talentspy.tenant.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * User entity to represent a {@link Talent} of the system.
 * 
 */
@Entity
@Table(name = "talents")
public class Talent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    @NotNull(message = "*Please provide your email")
    private String email;

    @JsonIgnore
    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotNull(message = "*Please provide your password")
    private String password;
    
    
    
    @Column(name = "status")
    private Integer status;
    
    //To do: change to LAZY
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "talent", cascade = CascadeType.ALL)
    private TalentProfile profile;

    @JsonIgnore
    @OneToMany(mappedBy = "talent", fetch = FetchType.LAZY)
    Set<Enrollment> enrollments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public TalentProfile getProfile() {
		return profile;
	}

	public void setProfile(TalentProfile profile) {
		this.profile = profile;
	}

	
	
	
    

}
