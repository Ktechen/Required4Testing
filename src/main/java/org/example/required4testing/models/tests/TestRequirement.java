package org.example.required4testing.models.tests;

import jakarta.persistence.*;
import org.example.required4testing.models.BaseEntity;
import org.example.required4testing.models.User;

import java.util.Collection;
import java.util.List;

@Entity
@Table
public class TestRequirement extends BaseEntity {
    private String Title;
    @Column(length = 1024)
    private String Description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn()
    private Collection<TestCase> TestCase;

    public TestRequirement() {
    }

    public TestRequirement(String title, String description, Collection<TestCase> testCase) {
        Title = title;
        Description = description;
        TestCase = testCase;
    }

    public void setTestCase(Collection<TestCase> testCase) {
        TestCase = testCase;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Collection<TestCase> getTestCase() {
        return TestCase;
    }

    public void setTestCase(List<TestCase> testCase) {
        TestCase = testCase;
    }
}
