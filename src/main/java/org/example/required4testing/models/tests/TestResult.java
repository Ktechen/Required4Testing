package org.example.required4testing.models.tests;

import jakarta.persistence.*;
import org.example.required4testing.models.BaseEntity;

@Entity
@Table
public class TestResult extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private TestResultType testResultType;

    public TestResult() {
    }

    public TestResult(TestResultType testResultType) {
        this.testResultType = testResultType;
    }
}
