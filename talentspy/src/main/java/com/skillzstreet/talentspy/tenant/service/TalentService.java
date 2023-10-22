package com.skillzstreet.talentspy.tenant.service;

import java.util.List;

import com.skillzstreet.talentspy.tenant.dto.NameValue;
import com.skillzstreet.talentspy.tenant.entity.Talent;

public interface TalentService {
	


	List<NameValue> getCountByGender();


	List<NameValue> getCountByDepartment();

	List<NameValue> getCountByJobTitle();

	List<NameValue> getCountByStatus();

	List<Talent> findAll();

}
