package org.example.required4testing.viewmodels;

import jakarta.el.MethodExpression;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.example.required4testing.dtos.UserDto;

import java.util.UUID;

@Named
@RequestScoped
public class TestCaseViewModel {
    private UUID id;
    private String name;
    private String description;
    private UserDto assignedUser;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDto getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(UserDto assignedUser) {
        this.assignedUser = assignedUser;
    }

    public void save() {
    }
}
