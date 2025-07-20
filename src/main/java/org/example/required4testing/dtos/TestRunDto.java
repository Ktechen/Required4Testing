package org.example.required4testing.dtos;

import java.util.Collection;

public class TestRunDto {
    private String Title;
    private Collection<TestCaseDto> testCases;

    public TestRunDto() {
    }

    public TestRunDto(String title, Collection<TestCaseDto> testCases) {
        Title = title;
        this.testCases = testCases;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Collection<TestCaseDto> getTestCases() {
        return testCases;
    }

    public void setTestCases(Collection<TestCaseDto> testCases) {
        this.testCases = testCases;
    }
}
