package org.example.required4testing.repositories.tests;

import org.example.required4testing.models.tests.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TestResultRepository extends JpaRepository<TestResult, UUID> {
}
