package com.skillstreet.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	
	private static final Logger LOG = LoggerFactory.getLogger(WebController.class);

    @GetMapping(value = "/{path:[^\\.]*}")
	public String redirect() {
    	LOG.debug("--------- Forward to / -------------");
    	return "forward:/";
    }
    
    @GetMapping(value = "/app/**")
	public String redirectApp() {
    	LOG.debug("--------- Forward to / -------------");
    	return "forward:/";
    }
    
    
}