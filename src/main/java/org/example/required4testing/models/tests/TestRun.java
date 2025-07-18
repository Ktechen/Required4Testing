package org.example.required4testing.models.tests;

import jakarta.persistence.*;
import org.example.required4testing.models.BaseEntity;

import java.util.Collection;

@Entity
@Table
public class TestRun extends BaseEntity {

    private String Title;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn()
    private Collection<TestResult> TestResults;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn()
    private Collection<TestCase> testCases;

    public TestRun() {
    }

    public TestRun(String title, Collection<TestResult> testResults, Collection<TestCase> testCases) {
        this.Title = title;
        this.TestResults = testResults;
        this.testCases = testCases;
    }

    public Collection<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(Collection<TestCase> testCases) {
        this.testCases = testCases;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Collection<TestResult> getTestResults() {
        return TestResults;
    }

    public void setTestResults(Collection<TestResult> testResults) {
        TestResults = testResults;
    }
}
