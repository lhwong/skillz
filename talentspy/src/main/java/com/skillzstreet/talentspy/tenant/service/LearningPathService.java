package com.skillzstreet.talentspy.tenant.service;

import java.util.List;

import com.skillzstreet.talentspy.tenant.entity.LearningPath;

public interface LearningPathService {
	
	
	
	LearningPath add(LearningPath learningPath);

	List<LearningPath> findAll();

}
