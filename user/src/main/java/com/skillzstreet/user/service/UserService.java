package com.skillzstreet.user.service;

import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.skillzstreet.user.command.AddUserCommand;

@Service
public class UserService{

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final CommandGateway commandGateway;

    public UserService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String> addUser(AddUserCommand command) {
        LOG.debug("Processing AddUserCommand command: {}", command);
        return this.commandGateway.send(command);
    }
}

