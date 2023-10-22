package com.skillzstreet.user.controller;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillzstreet.user.command.AddUserCommand;
import com.skillzstreet.user.service.UserService;

@RestController
public class UserController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    
	@GetMapping("/users")
    public CompletableFuture<String> addUser() {
		
		
		
        AddUserCommand command = new AddUserCommand(UUID.randomUUID().toString(), "name");
        LOG.info("Executing command: {}", command);
        return userService.addUser(command);
    }
	


}
