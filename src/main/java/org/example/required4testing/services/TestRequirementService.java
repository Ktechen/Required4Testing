package org.example.required4testing.services;

import jakarta.inject.Inject;
import org.example.required4testing.dtos.TestCaseDto;
import org.example.required4testing.dtos.TestRequirementDto;
import org.example.required4testing.dtos.UserDto;
import org.example.required4testing.models.UserLevelType;
import org.example.required4testing.models.tests.TestRequirement;
import org.example.required4testing.repositories.tests.TestCaseRepository;
import org.example.required4testing.repositories.tests.TestRequirementRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TestRequirementService {

    @Inject
    private TestRequirementRepository testRequirementRepository;

    @Inject
    private TestCaseRepository testCaseRepository;

    @Inject
    private UserService userService;

    public boolean create(UserDto userDto, TestRequirementDto requirement) {
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
                null
        );
        testRequirementRepository.save(testRequirement);
        return true;
    }

    public boolean update(UserDto userDto, TestCaseDto testCaseDto) {
        if(!UserLevelType.Testfallersteller.hasMinimumLevelRequirementsEngineer(userDto)) {
            return false;
        }

        var requirement = testRequirementRepository.findAll().stream().filter(t -> t.getTitle().equals(testCaseDto.getSelectedRequirement())).findFirst();
        if (requirement.isEmpty()) {
            return false;
        }

        var findTestCase = testCaseRepository
                .findAll()
                .stream()
                .filter(x -> x.getName().equalsIgnoreCase(testCaseDto.getName()))
                .findFirst()
                .orElse(null);

        if (findTestCase == null) {
            return false;
        }

        var testCases = requirement.get().getTestCase();
        testCases.add(findTestCase);
        requirement.get().setTestCase(testCases);

        testRequirementRepository.save(requirement.get());
        return true;
    }

    public Collection<TestRequirementDto> GetAll() {
        return this.testRequirementRepository.findAll().stream()
                .map(requirement -> new TestRequirementDto(
                        requirement.getTitle(),
                        requirement.getDescription(),
                        requirement.getTestCase() != null
                                ? requirement.getTestCase().stream()
                                .map(testCase -> new TestCaseDto(
                                        testCase.getName(),
                                        testCase.getDescription(),
                                        new UserDto(
                                                testCase.getAssignedToUser().getId(),
                                                testCase.getAssignedToUser().getName(),
                                                testCase.getAssignedToUser().getLevel()
                                        )
                                ))
                                .collect(Collectors.toList())
                                : Collections.emptyList()
                ))
                .collect(Collectors.toList());
    }
}
