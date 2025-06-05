package org.example.required4testing.repositories;

import org.example.required4testing.models.tests.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ITestCaseRepository extends JpaRepository<TestCase, UUID> {
}
