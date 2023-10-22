package com.skillzstreet.talentspy.tenant.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillzstreet.talentspy.tenant.entity.LearningPath;
import com.skillzstreet.talentspy.tenant.repository.LearningPathRepository;

@Service
@Transactional(value="tenantTransactionManager")
public class LearningPathServiceImpl implements LearningPathService {

    private static final Logger LOG = LoggerFactory
            .getLogger(LearningPathServiceImpl.class);

    @Autowired
    private LearningPathRepository learningPathRepository;

	@Override
	@Transactional(value="tenantTransactionManager", readOnly = true)
	public List<LearningPath> findAll() {
		return (List<LearningPath>) learningPathRepository.findAllByOrderByPathAscSequenceAsc();
	}

	@Override
	@Transactional(value="tenantTransactionManager", readOnly = false)
	public LearningPath add(LearningPath learningPath) {
		learningPath.setId(UUID.randomUUID());
		
		return learningPathRepository.save(learningPath);

	}

    
	
}
