package org.example.required4testing.services;

import jakarta.inject.Inject;
import org.example.required4testing.common.Result;
import org.example.required4testing.dtos.TestCaseDto;
import org.example.required4testing.dtos.TestRunDto;
import org.example.required4testing.dtos.UserDto;
import org.example.required4testing.models.User;
import org.example.required4testing.models.UserLevelType;
import org.example.required4testing.models.tests.TestCase;
import org.example.required4testing.models.tests.TestResult;
import org.example.required4testing.models.tests.TestRun;
import org.example.required4testing.repositories.tests.TestRunRepository;
import org.springframework.stereotype.Service;

@Service
public class TestRunService {

    @Inject
    private UserService userService;

    @Inject
    private TestRunRepository runRepository;

    public boolean Create(UserDto userDto, TestRunDto testRunDto) {
        if (!UserLevelType.Tester.hasMinimumLevelTester(userDto)) {
            return false;
        }

        var user = userService.GetUserByName(userDto.getName());
        if (!user.success()) {
            return false;
        }

        var convertTestResult = testRunDto.getTestResults()
                .stream()
                .map(x -> new TestResult(x.getTestResultType()))
                .toList();

        var convertTestCase = testRunDto.getTestCases()
                .stream()
                .map(x -> {
                    var userResult = userService.GetUserByName(x.getAssignedUser().getName());
                    if (userResult.success()) {
                        return new TestCase(x.getName(), x.getDescription(), userResult.object());
                    } else {
                        return new TestCase(x.getName(), x.getDescription(), null);
                    }
                })
                .toList();

        runRepository.save(new TestRun(testRunDto.getStartFrom(), convertTestResult, convertTestCase));

        return true;
    }
}
