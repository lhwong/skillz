package com.skillzstreet.talentspy.tenant.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.skillzstreet.talentspy.tenant.entity.LearningPath;

@Repository
public interface LearningPathRepository extends CrudRepository<LearningPath, UUID> {

	
	List<LearningPath> findAllByOrderByPathAscSequenceAsc();
	
	
}
