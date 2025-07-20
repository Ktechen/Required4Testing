package org.example.required4testing.services;

import jakarta.inject.Inject;
import org.example.required4testing.dtos.TestCaseDto;
import org.example.required4testing.dtos.UserDto;
import org.example.required4testing.models.User;
import org.example.required4testing.models.UserLevelType;
import org.example.required4testing.models.tests.TestCase;
import org.example.required4testing.repositories.tests.TestCaseRepository;
import org.example.required4testing.viewmodels.TestCaseViewModel;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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

        TestCase testcase = null;
        if(testCaseDto.getAssignedUser() != null) {
            var assignedUserName = testCaseDto.getAssignedUser().getName();
            var assignedUser = userService.GetUserByName(assignedUserName);

            if (!assignedUser.success()) {
                return false;
            }

            testcase = new TestCase(testCaseDto.getName(), testCaseDto.getDescription(), assignedUser.object());
        }else{
            testcase = new TestCase(testCaseDto.getName(), testCaseDto.getDescription());
        }
        testCaseRepository.save(Objects.requireNonNull(testcase));
        return true;
    }

    public Collection<TestCaseDto> GetAll() {
        return this.testCaseRepository.findAll().stream()
                .map(x -> {
                    var assignedUser = x.getAssignedToUser();
                    UserDto userDto = assignedUser != null
                            ? new UserDto(assignedUser.getId(), assignedUser.getName(), assignedUser.getLevel())
                            : new UserDto(null, "Not Set", -1);

                    return new TestCaseDto(
                            x.getName(),
                            x.getDescription(),
                            userDto
                    );
                })
                .collect(toList());
    }

    public boolean updateAssignedUser(TestCaseDto testCaseDto, User user) {
        var existingTestCase = this.testCaseRepository.findAll()
                .stream()
                .filter(x -> x.getName().equalsIgnoreCase(testCaseDto.getName()))
                .findFirst()
                .orElse(null);

        if(existingTestCase == null) {
            return false;
        }

        existingTestCase.setAssignedToUser(user);
        this.testCaseRepository.save(existingTestCase);
        return true;
    }

    public Collection<TestCaseDto> getFilteredByUser(UserDto user) {
        if (user == null) {
            return Collections.emptyList();
        }

        return this.testCaseRepository
                .findAll()
                .stream()
                .filter(x -> x.getAssignedToUser() != null
                        && x.getAssignedToUser().getName().equals(user.getName()))
                .map(x -> new TestCaseDto(x.getName(), x.getDescription(), user))
                .toList();
    }

    public boolean updateTestResult(TestCaseDto testCaseDto) {
        var testCaseOptional = this.testCaseRepository
                .findAll()
                .stream()
                .filter(x -> x.getName().equalsIgnoreCase(testCaseDto.getName()))
                .findFirst();

        if(testCaseOptional.isEmpty()) {
            return false;
        }

        var testcase = testCaseOptional.get();
        testcase.setTestResultType(testCaseDto.getTestResultType());

        this.testCaseRepository.save(testcase);
        return true;
    }
}
