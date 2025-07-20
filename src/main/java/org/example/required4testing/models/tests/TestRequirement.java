package org.example.required4testing.models.tests;

import jakarta.persistence.*;
import org.example.required4testing.models.BaseEntity;

import java.util.Collection;

@Entity
@Table
public class TestRequirement extends BaseEntity {

    @Column(unique=true)
    private String title;
    @Column(length = 1024)
    private String description;

    @OneToMany(mappedBy = "testRequirement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<TestCase> testCases;

    public TestRequirement() {
    }

    public TestRequirement(String title, String description, Collection<TestCase> testCase) {
        this.title = title;
        this.description = description;
        this.testCases = testCase;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(Collection<TestCase> testCases) {
        this.testCases = testCases;
    }
}
