package com.skillzstreet.talentspy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@RestController
//@EnableResourceServer
public class TalentspyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalentspyApplication.class, args);
	}
	
	
	

}
