package org.example.required4testing.repositories;

import org.example.required4testing.models.tests.TestRequirement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ITestRequirementRepository extends JpaRepository<TestRequirement, UUID> {
}
