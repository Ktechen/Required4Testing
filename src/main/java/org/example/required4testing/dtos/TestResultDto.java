package org.example.required4testing.dtos;

import org.example.required4testing.models.tests.TestResultType;

public class TestResultDto {
    private TestResultType testResultType;

    public TestResultType getTestResultType() {
        return testResultType;
    }

    public void setTestResultType(TestResultType testResultType) {
        this.testResultType = testResultType;
    }
}
