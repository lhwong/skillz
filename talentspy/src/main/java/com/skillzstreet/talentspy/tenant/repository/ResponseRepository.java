package com.skillzstreet.talentspy.tenant.repository;

import java.util.List;
import java.util.UUID;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skillzstreet.talentspy.tenant.dto.AssessmentScore;
import com.skillzstreet.talentspy.tenant.dto.NameValue;
import com.skillzstreet.talentspy.tenant.entity.Response;


public interface ResponseRepository extends JpaRepository<Response, Long> {

	@Query(nativeQuery = true)
	List<AssessmentScore> findScore();
	
	@Query("SELECT r FROM Response r WHERE r.assessment.id = ?1")
	List<Response> findByAssessment(UUID id);
	
	@Query("SELECT " +
	           "    new com.skillzstreet.talentspy.tenant.dto.NameValue(r.status, COUNT(r)) " +
	           "FROM " +
	           "    Response r " +
	           "GROUP BY " +
	           "    r.status")
	List<NameValue> getCounts();
	
	@Query(nativeQuery = true, value=" select " + 
			"	Cast(skill as varchar) sId, " + 
			"	count(*) " + 
			"	from ( " + 
			"	select " + 
			"	    assessment1_.PROFILE_ID as skill, " + 
			"		response0_.talent_id as talent " +  
			"    from " + 
			"        survey_responses response0_ " + 
			"    inner join " + 
			"        surveys assessment1_ " + 
			"            on (" + 
			"                response0_.survey_id=assessment1_.id " + 
			"            ) " + 
			"    group by " + 
			"        assessment1_.PROFILE_ID, " + 
			"        response0_.talent_id " + 
			"	order by " + 
			"		skill " + 
			"	) as tab " + 
			"	group by skill")
	List<Tuple> getTalentCountBySkill();
}
