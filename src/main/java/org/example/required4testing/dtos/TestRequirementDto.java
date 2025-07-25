package org.example.required4testing.dtos;

import java.util.Collection;

public class TestRequirementDto {
    private final String title;
    private final String description;
    private final Collection<TestCaseDto> testCase;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Collection<TestCaseDto> getTestCase() {
        return testCase;
    }

    public TestRequirementDto(String title, String description, Collection<TestCaseDto> testCase) {
        this.title = title;
        this.description = description;
        this.testCase = testCase;
    }
}
