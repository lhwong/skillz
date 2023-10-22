package com.skillzstreet.talentspy.tenant.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.skillzstreet.talentspy.tenant.dto.AssessmentScore;
import com.skillzstreet.talentspy.tenant.dto.NameValue;
import com.skillzstreet.talentspy.tenant.entity.Assessment;
import com.skillzstreet.talentspy.tenant.entity.Response;

public interface AssessmentService {

	

	List<Assessment> findAllAssessments();

	List<AssessmentScore> findAllAssessmentScores();

	List<Response> findAssessmentResponses(UUID id);

	List<NameValue> getCountByStatus();

	Map<String, BigInteger> getTalentCountBySkill();

}
