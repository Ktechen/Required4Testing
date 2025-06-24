package org.example.required4testing.models.tests;

import jakarta.persistence.*;
import org.example.required4testing.models.BaseEntity;

import java.util.Collection;

@Entity
@Table
public class TestRun extends BaseEntity {

    private String StartFrom;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn()
    private Collection<TestResult> TestResults;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn()
    private Collection<TestCase> testCases;

    public TestRun() {
    }

    public TestRun(String startFrom, Collection<TestResult> testResults, Collection<TestCase> testCases) {
        this.StartFrom = startFrom;
        this.TestResults = testResults;
        this.testCases = testCases;
    }

    public Collection<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(Collection<TestCase> testCases) {
        this.testCases = testCases;
    }

    public String getStartFrom() {
        return StartFrom;
    }

    public void setStartFrom(String startFrom) {
        StartFrom = startFrom;
    }

    public Collection<TestResult> getTestResults() {
        return TestResults;
    }

    public void setTestResults(Collection<TestResult> testResults) {
        TestResults = testResults;
    }
}
