package com.skillzstreet.talentspy.tenant.service;

import java.math.BigInteger;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Tuple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillzstreet.talentspy.tenant.dto.AssessmentScore;
import com.skillzstreet.talentspy.tenant.dto.NameValue;
import com.skillzstreet.talentspy.tenant.entity.Assessment;
import com.skillzstreet.talentspy.tenant.entity.Response;
import com.skillzstreet.talentspy.tenant.repository.AssessmentRepository;
import com.skillzstreet.talentspy.tenant.repository.ResponseRepository;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    private static final Logger LOG = LoggerFactory
            .getLogger(AssessmentServiceImpl.class);

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private ResponseRepository responseRepository;

	@Override
	public List<Assessment> findAllAssessments() {
		return assessmentRepository.findAll();
	}
	
	@Override
	public List<AssessmentScore> findAllAssessmentScores() {
		return responseRepository.findScore();
	}
	
	@Override
	public List<Response> findAssessmentResponses(UUID id) {
		return responseRepository.findByAssessment(id);
	}
	
	@Override
	public List<NameValue> getCountByStatus() {
		
		return responseRepository.getCounts();

	}
	
	@Override
	public Map<String, BigInteger> getTalentCountBySkill() {
		
		Map<String, BigInteger> values = new Hashtable<String, BigInteger>();
		
		List<Tuple> tuples = responseRepository.getTalentCountBySkill();
		
		for(Tuple t: tuples) {
			values.put((String)t.get(0), (BigInteger)t.get(1));
		}
		
		return values;
	}
	
}
