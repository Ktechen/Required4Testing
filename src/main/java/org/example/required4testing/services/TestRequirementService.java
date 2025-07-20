package org.example.required4testing.services;

import jakarta.inject.Inject;
import org.example.required4testing.dtos.TestCaseDto;
import org.example.required4testing.dtos.TestRequirementDto;
import org.example.required4testing.dtos.UserDto;
import org.example.required4testing.models.User;
import org.example.required4testing.models.UserLevelType;
import org.example.required4testing.models.tests.TestRequirement;
import org.example.required4testing.repositories.tests.TestCaseRepository;
import org.example.required4testing.repositories.tests.TestRequirementRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
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

    public boolean update(UserDto userDto, TestCaseDto testCaseDto, TestRequirementDto requirementDto) {
        if(!UserLevelType.Testfallersteller.hasMinimumLevelRequirementsEngineer(userDto)) {
            return false;
        }

        var requirement = testRequirementRepository.findAll().stream().filter(t -> t.getTitle().equals(requirementDto.getTitle())).findFirst();
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

        findTestCase.setTestRequirement(requirement.get());

        testCaseRepository.save(findTestCase);
        return true;
    }

    public Collection<TestRequirementDto> GetAll() {
        return testRequirementRepository.findAll().stream()
                .map(requirement -> new TestRequirementDto(
                        requirement.getTitle(),
                        requirement.getDescription(),
                        requirement.getTestCases() == null
                                ? Collections.emptyList()
                                : requirement.getTestCases().stream()
                                .map(testCase -> {
                                    User assigned = testCase.getAssignedToUser();
                                    return new TestCaseDto(
                                            testCase.getName(),
                                            testCase.getDescription(),
                                            assigned == null
                                                    ? new UserDto()
                                                    : new UserDto(
                                                    assigned.getId(),
                                                    assigned.getName(),
                                                    assigned.getLevel()
                                            )
                                    );
                                })
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}
