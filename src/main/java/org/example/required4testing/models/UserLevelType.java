package org.example.required4testing.models;

import org.example.required4testing.dtos.UserDto;

import java.util.Arrays;

public enum UserLevelType {
    Tester(0),
    Testfallersteller(10),
    Testmanager(20),
    RequirementsEngineer(30);

    private final int value;

    UserLevelType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UserLevelType fromLevel(int level) {
        return Arrays.stream(values())
                .filter(type -> type.value == level)
                .findFirst()
                .orElse(null);
    }

    public boolean hasMinimumLevelRequirementsEngineer(UserDto userDto) {
        return userDto.getLevel() >= UserLevelType.RequirementsEngineer.getValue();
    }

    public boolean hasMinimumLevelTestmanager(UserDto userDto) {
        return userDto.getLevel() >= UserLevelType.Testmanager.getValue();
    }


    public boolean hasMinimumLevelTestfallersteller(UserDto userDto) {
        return userDto.getLevel() >= UserLevelType.Testfallersteller.getValue();
    }


    public boolean hasMinimumLevelTester(UserDto userDto) {
        return userDto.getLevel() >= UserLevelType.Tester.getValue();
    }
}
