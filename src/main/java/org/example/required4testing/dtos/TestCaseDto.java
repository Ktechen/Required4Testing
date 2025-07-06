package org.example.required4testing.dtos;

public class TestCaseDto {
    private String name;
    private String description;
    private UserDto assignedUser;

    public TestCaseDto() {

    }

    public TestCaseDto(String name, String description, UserDto assignedUser) {
        this.name = name;
        this.description = description;
        this.assignedUser = assignedUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAssignedUser(UserDto assignedUser) {
        this.assignedUser = assignedUser;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UserDto getAssignedUser() {
        return assignedUser;
    }
}

