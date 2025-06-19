package org.example.required4testing.services;

import jakarta.inject.Inject;
import org.example.required4testing.dtos.TestRequirementDto;
import org.example.required4testing.dtos.UserDto;
import org.example.required4testing.models.UserLevelType;
import org.example.required4testing.models.tests.TestRequirement;
import org.example.required4testing.repositories.tests.TestRequirementRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class TestRequirementService {

    @Inject
    private TestRequirementRepository testRequirementRepository;

    @Inject
    private UserService userService;

    public boolean CreateTestsRequirements(UserDto userDto, TestRequirementDto requirement) {
        if (!UserLevelType.RequirementsEngineer.hasMinimumLevelRequirementsEngineer(userDto)) {
            return false;
        }

        var user = userService.GetUserByName(userDto.getName());
        if (!user.success()) {
            return false;
        }

        var testRequirement = new TestRequirement(
                requirement.getTitle(),
                requirement.getDescription(),
                requirement.getTestCase()
        );
        testRequirementRepository.save(testRequirement);
        return true;
    }

    public Collection<TestRequirementDto> GetAll() {
        return this.testRequirementRepository.findAll().stream()
                .map(x -> new TestRequirementDto(x.getTitle(), x.getDescription(), x.getTestCase()))
                .collect(Collectors.toList());
    }

}
