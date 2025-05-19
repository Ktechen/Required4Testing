package org.example.required4testing.models.tests;

import jakarta.persistence.*;
import org.example.required4testing.models.BaseEntity;

import java.util.List;

@Entity
@Table
public class TestRequirement extends BaseEntity {
    private String Title;
    @Column(length = 1024)
    private String Description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn()
    private List<TestCase> TestCase;

    public TestRequirement() {
    }

    public TestRequirement(String title, String description) {
        Title = title;
        Description = description;
    }
}
