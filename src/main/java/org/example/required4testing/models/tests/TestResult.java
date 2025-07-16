package org.example.required4testing.models.tests;

import jakarta.persistence.*;
import org.example.required4testing.models.BaseEntity;

import java.util.UUID;

@Entity
@Table
public class TestResult extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private TestResultType testResultType;

    public TestResult() {
    }

    public TestResult(UUID id, TestResultType testResultType) {
        super(id);
        this.testResultType = testResultType;
    }

    public void setTestResultType(TestResultType testResultType) {
        this.testResultType = testResultType;
    }

    public TestResultType getTestResultType() {
        return testResultType;
    }
}
