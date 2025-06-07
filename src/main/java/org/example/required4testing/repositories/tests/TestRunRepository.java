package org.example.required4testing.repositories.tests;

import org.example.required4testing.models.tests.TestRun;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TestRunRepository extends JpaRepository<TestRun, UUID> {
}
