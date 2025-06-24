package org.example.required4testing.dtos;

import java.util.Collection;

public class TestRunDto {
    private String StartFrom;
    private Collection<TestResultDto> TestResults;
    private Collection<TestCaseDto> testCases;

    public TestRunDto() {
    }

    public TestRunDto(String startFrom, Collection<TestResultDto> testResults, Collection<TestCaseDto> testCases) {
        StartFrom = startFrom;
        TestResults = testResults;
        this.testCases = testCases;
    }

    public String getStartFrom() {
        return StartFrom;
    }

    public void setStartFrom(String startFrom) {
        StartFrom = startFrom;
    }

    public Collection<TestResultDto> getTestResults() {
        return TestResults;
    }

    public void setTestResults(Collection<TestResultDto> testResults) {
        TestResults = testResults;
    }

    public Collection<TestCaseDto> getTestCases() {
        return testCases;
    }

    public void setTestCases(Collection<TestCaseDto> testCases) {
        this.testCases = testCases;
    }
}
