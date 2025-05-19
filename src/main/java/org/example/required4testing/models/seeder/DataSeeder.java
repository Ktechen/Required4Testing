package org.example.required4testing.models.seeder;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import org.example.required4testing.models.UserLevelType;
import org.example.required4testing.repositories.IUserLevelRepository;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder {

    @Inject
    private IUserLevelRepository userLevelRepository;

    @PostConstruct
    public void AddLevel() {
        if (userLevelRepository.count() != 0) {
            return;
        }
        userLevelRepository.saveAndFlush(new UserLevelType(0, "Tester"));
        userLevelRepository.saveAndFlush(new UserLevelType(10, "Testfallersteller"));
        userLevelRepository.saveAndFlush(new UserLevelType(20, "Testmanager"));
        userLevelRepository.saveAndFlush(new UserLevelType(30, "Requirements Engineer"));
    }
}
