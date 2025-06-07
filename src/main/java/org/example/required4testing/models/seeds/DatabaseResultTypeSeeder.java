package org.example.required4testing.models.seeds;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import org.example.required4testing.models.tests.TestResultType;
import org.example.required4testing.repositories.tests.TestResultTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class DatabaseResultTypeSeeder {
    @Inject
    private TestResultTypeRepository resultTypeRepository;

    @PostConstruct
    public void seedDatabase() {
        if (resultTypeRepository.count() != 0) return;

        resultTypeRepository.save(new TestResultType(0, "Stopped"));
        resultTypeRepository.save(new TestResultType(10, "Failed"));
        resultTypeRepository.save(new TestResultType(20, "Success"));
        resultTypeRepository.save(new TestResultType(21, "Success with Warnings"));
    }
}
