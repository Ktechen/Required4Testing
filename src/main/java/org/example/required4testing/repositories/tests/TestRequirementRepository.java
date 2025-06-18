package org.example.required4testing.repositories.tests;

import org.example.required4testing.models.tests.TestRequirement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TestRequirementRepository extends JpaRepository<TestRequirement, UUID> {

}
