package com.skillzstreet.talentspy.tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skillzstreet.talentspy.tenant.dto.NameValue;
import com.skillzstreet.talentspy.tenant.entity.Talent;

public interface TalentRepository extends JpaRepository<Talent, Long> {


	@Query("SELECT " +
	           "    new com.skillzstreet.talentspy.tenant.dto.NameValue(p.gender, COUNT(p)) " +
	           "FROM " +
	           "    TalentProfile p " +
	           "GROUP BY " +
	           "    p.gender")
	List<NameValue> getCountByGender();
	
	@Query("SELECT " +
	           "    new com.skillzstreet.talentspy.tenant.dto.NameValue(d.name, COUNT(p)) " +
	           "FROM " +
	           "    TalentProfile p JOIN p.department d " +
	           "GROUP BY " +
	           "    d.id " +
			   "ORDER BY " +
			   "	COUNT(p) DESC")
	List<NameValue> getCountByDepartment();
	
	
	@Query("SELECT " +
	           "    new com.skillzstreet.talentspy.tenant.dto.NameValue(p.jobTitle, COUNT(p)) " +
	           "FROM " +
	           "    TalentProfile p " +
	           "GROUP BY " +
	           "    p.jobTitle " + 
	           "ORDER BY " +
	           "	COUNT(p) DESC")
	List<NameValue> getCountByJobTitle();

	@Query("SELECT " +
	           "    new com.skillzstreet.talentspy.tenant.dto.NameValue(t.status, COUNT(t)) " +
	           "FROM " +
	           "    Talent t " +
	           "GROUP BY " +
	           "    t.status")
	List<NameValue> getCountByStatus();
}
