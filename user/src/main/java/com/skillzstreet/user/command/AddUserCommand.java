package com.skillzstreet.user.command;

public class AddUserCommand {
	
	private final String id;
    private final String name;

    public AddUserCommand(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AddUserCommand{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
