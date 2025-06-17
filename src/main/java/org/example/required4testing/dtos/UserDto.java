package org.example.required4testing.dtos;

public class UserDto {
    private String name;
    private int Level;

    public UserDto() {
    }

    public UserDto(String name, int Level) {
        this.name = name;
        this.Level = Level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        this.Level = level;
    }
}
