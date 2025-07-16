package org.example.required4testing.services;

import jakarta.inject.Inject;
import org.example.required4testing.dtos.TestCaseDto;
import org.example.required4testing.dtos.TestResultDto;
import org.example.required4testing.dtos.TestRunDto;
import org.example.required4testing.dtos.UserDto;
import org.example.required4testing.models.UserLevelType;
import org.example.required4testing.models.tests.TestCase;
import org.example.required4testing.models.tests.TestResult;
import org.example.required4testing.models.tests.TestResultType;
import org.example.required4testing.models.tests.TestRun;
import org.example.required4testing.repositories.tests.TestRunRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestRunService {

    @Inject
    private UserService userService;

    @Inject
    private TestRunRepository runRepository;

    public Collection<TestRunDto> getAll() {
        return this.runRepository
                .findAll()
                .stream()
                .map(x -> new TestRunDto(
                        x.getTitle(),
                        x.getTestResults()
                                .stream()
                                .map(y -> new TestResultDto(x.getId()))
                                .toList(),
                        x.getTestCases()
                                .stream()
                                .map(z -> new TestCaseDto(
                                        z.getName(),
                                        z.getDescription(),
                                        new UserDto(
                                                z.getAssignedToUser().getId(),
                                                z.getAssignedToUser().getName(),
                                                z.getAssignedToUser().getLevel()
                                        )
                                ))
                                .toList()
                ))
                .toList();

    }


    public boolean Create(UserDto userDto, TestRunDto testRunDto) {
        if (!UserLevelType.Tester.hasMinimumLevelTester(userDto)) {
            return false;
        }

        var user = userService.GetUserByName(userDto.getName());
        if (!user.success()) {
            return false;
        }

        List<TestResult> convertTestResult = null;
        if(testRunDto.getTestResults() != null) {
             convertTestResult = testRunDto.getTestResults()
                    .stream()
                    .map(x -> new TestResult(x.getId(), new TestResultType(0, "Option")))
                    .toList();
        }

        List<TestCase> convertTestCase = null;
        if(testRunDto.getTestCases() != null) {
            convertTestCase = testRunDto.getTestCases()
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
        }

        runRepository.save(new TestRun(testRunDto.getTitle(), convertTestResult, convertTestCase));

        return true;
    }


}
