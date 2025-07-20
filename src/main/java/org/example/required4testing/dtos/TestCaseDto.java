package org.example.required4testing.dtos;

import org.example.required4testing.models.TestResultType;

public class TestCaseDto {
    private String name;
    private String description;
    private UserDto assignedUser;

    private TestResultType testResultType;

    public TestCaseDto(String name, String description, UserDto assignedUser) {
        this.name = name;
        this.description = description;
        this.assignedUser = assignedUser;
    }

    public TestCaseDto(String name, String description, UserDto assignedUser, TestResultType testResultType) {
        this.name = name;
        this.description = description;
        this.assignedUser = assignedUser;
        this.testResultType = testResultType;
    }

    public TestCaseDto() {
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

    public TestResultType getTestResultType() {
        return testResultType;
    }

    public void setTestResultType(TestResultType testResultType) {
        this.testResultType = testResultType;
    }
}

