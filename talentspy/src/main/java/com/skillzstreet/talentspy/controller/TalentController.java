package com.skillzstreet.talentspy.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skillzstreet.talentspy.tenant.dto.NameValue;
import com.skillzstreet.talentspy.tenant.entity.LearningPath;
import com.skillzstreet.talentspy.tenant.entity.Talent;
import com.skillzstreet.talentspy.tenant.service.TalentService;





@RestController
@RequestMapping("/talents")
public class TalentController {
	private static final Logger LOG = LoggerFactory.getLogger(TalentController.class);
	
	@Autowired
	private TalentService talentService;
	
    public TalentController() {
        
    }

    @RequestMapping(method = RequestMethod.GET)
	public List<Talent> findAll() {
    	
    	
		return talentService.findAll();
	}
    
    @RequestMapping("/")
	public Message home() {
    	for(Talent t : talentService.findAll()) {
    		LOG.debug(t.getEmail());
    	}
    	
		return new Message("Hello World");
	}
    
    
    @RequestMapping("/countbygender")
	public List<NameValue> getCountByGender() {
    	return talentService.getCountByGender();
	}
	
    @RequestMapping("/countbydepartment")
	public List<NameValue> getCountByDepartment() {
    	return talentService.getCountByDepartment();
	}
    
    @RequestMapping("/countbyjobtitle")
	public List<NameValue> getCountByJobTitle() {
    	return talentService.getCountByJobTitle();
	}
    
    @RequestMapping("/countbystatus")
	public List<NameValue> getCountByStatus() {
    	return talentService.getCountByStatus();
	}
    

}


class Message {
	private String id = UUID.randomUUID().toString();
	private String content;

	Message() {
	}

	public Message(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}