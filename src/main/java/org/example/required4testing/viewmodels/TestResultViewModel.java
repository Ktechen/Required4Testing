package org.example.required4testing.viewmodels;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.example.required4testing.models.tests.TestResult;
import org.example.required4testing.models.tests.TestResultType;
import org.example.required4testing.models.tests.TestRun;

import java.util.UUID;

@Named
@RequestScoped
public class TestResultViewModel {
    private UUID testRunId;
    private TestResult testResult;
    private int testResultTypeId;
    private TestResultType testResultType;

    public void save(){

    }

    private TestRun testRun;

    public TestRun getTestRun() {
        return testRun;
    }

    public void setTestRun(TestRun testRun) {
        this.testRun = testRun;
    }

    public TestResult getTestResult() {
        return testResult;
    }

    public void setTestResult(TestResult testResult) {
        this.testResult = testResult;
    }

    public TestResultType getTestResultType() {
        return testResultType;
    }

    public void setTestResultType(TestResultType testResultType) {
        this.testResultType = testResultType;
    }

    public int getTestResultTypeId() {
        return testResultTypeId;
    }

    public void setTestResultTypeId(int testResultTypeId) {
        this.testResultTypeId = testResultTypeId;
    }

    public UUID getTestRunId() {
        return testRunId;
    }

    public void setTestRunId(UUID testRunId) {
        this.testRunId = testRunId;
    }
}
