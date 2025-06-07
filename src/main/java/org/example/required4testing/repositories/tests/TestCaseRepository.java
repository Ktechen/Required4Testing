package org.example.required4testing.repositories.tests;

import org.example.required4testing.models.tests.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TestCaseRepository extends JpaRepository<TestCase, UUID> {
}
