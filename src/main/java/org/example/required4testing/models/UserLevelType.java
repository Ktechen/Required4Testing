package org.example.required4testing.models;

import jakarta.persistence.*;

@Entity
@Table
public class UserLevelType {

    @Id
    private int Id;

    @Column(nullable = false)
    private String Title;

    public UserLevelType() {
    }

    public UserLevelType(int id, String title) {
        Id = id;
        Title = title;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
