package org.example.required4testing.models;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
public class User extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(unique = true, nullable = false)
    private UserLevelType level;

    public User() {
    }

    public User(String name, UserLevelType level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserLevelType getLevel() {
        return level;
    }

    public void setLevel(UserLevelType level) {
        this.level = level;
    }
}
