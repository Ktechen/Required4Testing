package org.example.required4testing.models.tests;

import jakarta.persistence.*;
import org.example.required4testing.models.BaseEntity;
import org.example.required4testing.models.User;

@Entity
@Table
public class TestCase extends BaseEntity {
    private String name;
    private String description;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User AssignedToUser;

    public TestCase() {
    }

    public TestCase(String name, String description, User assignedToUser) {
        this.name = name;
        this.description = description;
        AssignedToUser = assignedToUser;
    }

    public User getAssignedToUser() {
        return AssignedToUser;
    }

    public void setAssignedToUser(User assignedToUser) {
        AssignedToUser = assignedToUser;
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
}
