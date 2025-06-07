package org.example.required4testing.models;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
public class User extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    public User() {
    }

    public User(String name, String password, int level) {
        this.name = name;
        this.password = password;
        this.level = level;
    }

    private int level;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
