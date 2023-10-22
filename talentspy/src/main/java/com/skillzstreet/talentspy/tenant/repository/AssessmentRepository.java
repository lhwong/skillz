package com.skillzstreet.talentspy.tenant.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skillzstreet.talentspy.tenant.entity.Assessment;


public interface AssessmentRepository extends JpaRepository<Assessment, UUID> {

	@Query("SELECT " +
	           "    a " +
	           "FROM " +
	           "    Assessment a INNER JOIN FETCH a.responses r INNER JOIN FETCH a.skill s")
	List<Assessment> findAll();
	
	
	
}
