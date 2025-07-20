package org.example.required4testing.services;

import jakarta.inject.Inject;
import org.example.required4testing.dtos.TestCaseDto;
import org.example.required4testing.dtos.TestRunDto;
import org.example.required4testing.dtos.UserDto;
import org.example.required4testing.models.UserLevelType;
import org.example.required4testing.models.tests.TestCase;
import org.example.required4testing.models.tests.TestRun;
import org.example.required4testing.repositories.tests.TestCaseRepository;
import org.example.required4testing.repositories.tests.TestRunRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class TestRunService {

    @Inject
    private UserService userService;

    @Inject
    private TestRunRepository runRepository;

    @Inject
    private TestCaseRepository testCaseRepository;

    @Inject
    private TestRunRepository testRunRepository;

    public Collection<TestRunDto> getAll() {
        return this.runRepository
                .findAll()
                .stream()
                .map(x -> new TestRunDto(
                        x.getTitle(),
                        x.getTestCases()
                                .stream()
                                .map(z -> new TestCaseDto(
                                        z.getName(),
                                        z.getDescription(),
                                        z.getAssignedToUser() != null
                                                ? new UserDto(
                                                z.getAssignedToUser().getId(),
                                                z.getAssignedToUser().getName(),
                                                z.getAssignedToUser().getLevel()
                                        ) : null
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

        List<TestCase> convertTestCase = null;
        if (testRunDto.getTestCases() != null) {
            convertTestCase = testRunDto.getTestCases()
                    .stream()
                    .map(x -> {
                        var userResult = userService.GetUserByName(x.getAssignedUser().getName());
                        if (userResult.success()) {
                            return new TestCase(x.getName(), x.getDescription(), userResult.object());
                        } else {
                            return new TestCase(x.getName(), x.getDescription());
                        }
                    })
                    .toList();
        }

        runRepository.save(new TestRun(testRunDto.getTitle(), convertTestCase));

        return true;
    }


    public boolean updateTestRun(UserDto userDto, TestCaseDto testCaseDto, TestRunDto testRunDto) {
        if (!UserLevelType.Testmanager.hasMinimumLevelTester(userDto)) {
            return false;
        }

        var testRun = testRunRepository.findAll().stream().filter(t -> t.getTitle().equals(testRunDto.getTitle())).findFirst();
        if (testRun.isEmpty()) {
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

        findTestCase.setTestRun(testRun.get());

        testCaseRepository.save(findTestCase);
        return true;
    }

}
