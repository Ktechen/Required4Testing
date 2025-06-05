package org.example.required4testing.dtos;

import org.example.required4testing.models.tests.TestCase;

import java.util.Collection;

public class TestRequirementDto {
    private String Title;
    private String Description;

    private Collection<TestCase> TestCase;

    public TestRequirementDto() {
    }

    public TestRequirementDto(String title, String description, Collection<TestCase> testCase) {
        Title = title;
        Description = description;
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

    public void setTestCase(Collection<TestCase> testCase) {
        TestCase = testCase;
    }
}
