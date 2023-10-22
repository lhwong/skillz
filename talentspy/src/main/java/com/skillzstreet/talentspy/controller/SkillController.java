package com.skillzstreet.talentspy.controller;



import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.skillzstreet.talentspy.tenant.entity.Skill;
import com.skillzstreet.talentspy.tenant.entity.Views;
import com.skillzstreet.talentspy.tenant.service.AssessmentService;
import com.skillzstreet.talentspy.tenant.service.SkillService;







@RestController
@RequestMapping("/skills")
public class SkillController {
	private static final Logger LOG = LoggerFactory.getLogger(SkillController.class);
	
	@Autowired
	private SkillService skillService;
	
	@Autowired
	private AssessmentService assessmentService;
	
	@JsonView(Views.Goal.class)
	@RequestMapping("")
    public List<Skill> skillController() {
		
		return skillService.findAllSkills();
    }

    

    
	@RequestMapping("/talentassessed")
    public Map<String, BigInteger> talentAssessedController() {
		
		return assessmentService.getTalentCountBySkill();
		
        
    }

    

}


