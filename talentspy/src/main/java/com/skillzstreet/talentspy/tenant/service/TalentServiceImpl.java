package com.skillzstreet.talentspy.tenant.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillzstreet.talentspy.tenant.dto.NameValue;
import com.skillzstreet.talentspy.tenant.entity.Talent;
import com.skillzstreet.talentspy.tenant.repository.TalentRepository;

@Service
public class TalentServiceImpl implements TalentService {

    private static final Logger LOG = LoggerFactory
            .getLogger(TalentServiceImpl.class);

    @Autowired
    private TalentRepository talentRepository;

	@Override
	public List<Talent> findAll() {
		
		return talentRepository.findAll();
	}
	
	
	@Override
	public List<NameValue> getCountByGender() {
		
		return talentRepository.getCountByGender();

	}


	@Override
	public List<NameValue> getCountByDepartment() {
		
		return talentRepository.getCountByDepartment();
	}


	@Override
	public List<NameValue> getCountByJobTitle() {
		return talentRepository.getCountByJobTitle();
	}


	@Override
	public List<NameValue> getCountByStatus() {
		return talentRepository.getCountByStatus();
	}	
}
