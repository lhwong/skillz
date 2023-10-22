package com.skillzstreet.talentspy.tenant.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillzstreet.talentspy.tenant.entity.Skill;
import com.skillzstreet.talentspy.tenant.repository.SkillRepository;

@Service
public class SkillServiceImpl implements SkillService {

    private static final Logger LOG = LoggerFactory
            .getLogger(SkillServiceImpl.class);

    @Autowired
    private SkillRepository skillRepository;



	@Override
	public List<Skill> findAllSkills() {
		return skillRepository.findAll();
	}
	
	
	
}
