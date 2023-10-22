package com.skillzstreet.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.serialization.Revision;

@Revision("1.0")
public class UserAddedEvent {
	
	@TargetAggregateIdentifier
	private String id;
    private String name;

    public UserAddedEvent() {
    }

    public UserAddedEvent(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

}

