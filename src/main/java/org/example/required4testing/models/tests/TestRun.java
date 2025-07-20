package org.example.required4testing.models.tests;

import jakarta.persistence.*;
import org.example.required4testing.models.BaseEntity;
import org.example.required4testing.models.TestResultType;

import java.util.Collection;

@Entity
@Table
public class TestRun extends BaseEntity {

    private String title;

    @OneToMany(mappedBy = "testRun", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<TestCase> testCases;

    public TestRun() {
    }

    public TestRun(String title, Collection<TestCase> testCases) {
        this.title = title;
        this.testCases = testCases;
    }

    public Collection<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(Collection<TestCase> testCases) {
        this.testCases = testCases;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
