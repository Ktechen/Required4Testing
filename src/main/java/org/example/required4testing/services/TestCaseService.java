package org.example.required4testing.services;

import jakarta.inject.Inject;
import org.example.required4testing.dtos.TestCaseDto;
import org.example.required4testing.dtos.TestRequirementDto;
import org.example.required4testing.dtos.UserDto;
import org.example.required4testing.models.UserLevelType;
import org.example.required4testing.models.tests.TestCase;
import org.example.required4testing.models.tests.TestRequirement;
import org.example.required4testing.repositories.tests.TestCaseRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class TestCaseService {

    @Inject
    private TestCaseRepository testCaseRepository;

    @Inject
    private UserService userService;

    public boolean create(UserDto userDto, TestCaseDto testCaseDto) {
        if (!UserLevelType.Testmanager.hasMinimumLevelRequirementsEngineer(userDto)) {
            return false;
        }

        var user = userService.GetUserByName(userDto.getName());
        if (!user.success()) {
            return false;
        }

        var assignedUser = userService.GetUserByName(testCaseDto.getName());
        var testcase = new TestCase(testCaseDto.getName(), testCaseDto.getDescription(), assignedUser.object());
        testCaseRepository.save(testcase);
        return true;
    }

    public Collection<TestCaseDto> GetAll() {
        return this.testCaseRepository.findAll().stream()
                .map(x ->
                        new TestCaseDto(
                                x.getName(),
                                x.getDescription(),
                                new UserDto(
                                        x.getAssignedToUser().getName(),
                                        x.getAssignedToUser().getLevel())
                        ))
                .collect(Collectors.toList());
    }
}
