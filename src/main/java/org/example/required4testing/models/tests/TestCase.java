package org.example.required4testing.models.tests;

import jakarta.persistence.*;
import org.example.required4testing.models.BaseEntity;
import org.example.required4testing.models.TestResultType;
import org.example.required4testing.models.User;

@Entity
@Table
public class TestCase extends BaseEntity {

    @Column(unique=true)
    private String name;
    private String description;

    private TestResultType testResultType;

    @ManyToOne
    @JoinColumn(name = "test_run_id")
    private TestRun testRun;

    @ManyToOne
    @JoinColumn(name = "requirement_id")
    private TestRequirement testRequirement;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User assignedToUser;

    public TestCase() {
    }

    public TestCase(String name, String description) {
        this.name = name;
        this.description = description;
        this.testRun = null;
        this.testRequirement = null;
        this.assignedToUser = null;
        this.testResultType = TestResultType.failed;
    }

    public TestCase(String name, String description, User assignedToUser) {
        this.name = name;
        this.description = description;
        this.assignedToUser = assignedToUser;
        this.testRun = null;
        this.testRequirement = null;
        this.testResultType = TestResultType.failed;
    }

    public TestCase(String name, String description, TestRequirement testRequirement, User assignedToUser, TestRun testRun) {
        this.name = name;
        this.description = description;
        this.testRun = testRun;
        this.testRequirement = testRequirement;
        this.assignedToUser = assignedToUser;
        this.testResultType = TestResultType.failed;
    }

    public TestCase(String name, String description, TestRequirement testRequirement, User assignedToUser, TestRun testRun, TestResultType testResultType) {
        this.name = name;
        this.description = description;
        this.testRun = testRun;
        this.testRequirement = testRequirement;
        this.assignedToUser = assignedToUser;
        this.testResultType = testResultType;
    }

    public TestResultType getTestResultType() {
        return testResultType;
    }

    public void setTestResultType(TestResultType testResultType) {
        this.testResultType = testResultType;
    }

    public TestRun getTestRun() {
        return testRun;
    }

    public void setTestRun(TestRun testRun) {
        this.testRun = testRun;
    }

    public TestRequirement getTestRequirement() {
        return testRequirement;
    }

    public void setTestRequirement(TestRequirement requirement) {
        this.testRequirement = requirement;
    }

    public User getAssignedToUser() {
        return assignedToUser;
    }

    public void setAssignedToUser(User assignedToUser) {
        this.assignedToUser = assignedToUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
