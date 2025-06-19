package org.example.required4testing.dtos;

import java.util.UUID;

public class TestCaseDto {
    private UUID id;
    private String name;
    private String description;
    private UserDto assignedUser;

    public TestCaseDto(UUID id, String name, String description, UserDto assignedUser) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.assignedUser = assignedUser;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public UserDto getAssignedUser() { return assignedUser; }
}

