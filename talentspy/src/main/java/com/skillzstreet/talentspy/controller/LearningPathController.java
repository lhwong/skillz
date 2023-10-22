package com.skillzstreet.talentspy.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.skillzstreet.talentspy.tenant.entity.LearningPath;
import com.skillzstreet.talentspy.tenant.service.LearningPathService;

@RestController
@RequestMapping("/learning-paths")
public class LearningPathController {
	
	
	@Autowired
	private LearningPathService learningPathService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public List<LearningPath> findAll() {
    	
    	
		return learningPathService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public LearningPath add(@RequestBody LearningPath learningPath) {
		return learningPathService.add(learningPath);
	}

}
