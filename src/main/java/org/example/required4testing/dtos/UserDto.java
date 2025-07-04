package org.example.required4testing.dtos;

import java.util.UUID;

public class UserDto {

    private UUID uuid;
    private String name;
    private int level;

    public UserDto() {
    }

    public UserDto(UUID uuid, String name, int level) {
        this.uuid = uuid;
        this.name = name;
        this.level = level;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

