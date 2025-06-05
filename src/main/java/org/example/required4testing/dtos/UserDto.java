package org.example.required4testing.dtos;

import org.example.required4testing.models.UserLevelType;

public class UserDto {
    private String name;
    private UserLevelType userLevelType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserLevelType getUserLevelType() {
        return userLevelType;
    }

    public void setUserLevelType(UserLevelType userLevelType) {
        this.userLevelType = userLevelType;
    }
}
