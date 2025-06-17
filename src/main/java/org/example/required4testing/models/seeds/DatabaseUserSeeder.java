package org.example.required4testing.models.seeds;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import org.example.required4testing.models.User;
import org.example.required4testing.models.UserLevelType;
import org.example.required4testing.repositories.tests.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserSeeder {
    @Inject
    private UserRepository userRepository;

    @PostConstruct
    public void seedDatabase() {
        if (userRepository.count() != 0) return;
        userRepository.save(new User("Peter", "1234", UserLevelType.RequirementsEngineer.getValue()));
        userRepository.save(new User("Hans", "1234", UserLevelType.Testmanager.getValue()));
        userRepository.save(new User("Jürgen", "1234", UserLevelType.Testfallersteller.getValue()));
        userRepository.save(new User("Lukas", "1234", UserLevelType.Tester.getValue()));
    }
}
