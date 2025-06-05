package org.example.required4testing.models;

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
}
