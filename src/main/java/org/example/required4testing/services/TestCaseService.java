package org.example.required4testing.services;

import jakarta.inject.Inject;
import org.example.required4testing.dtos.TestCaseDto;
import org.example.required4testing.dtos.UserDto;
import org.example.required4testing.models.UserLevelType;
import org.example.required4testing.models.tests.TestCase;
import org.example.required4testing.repositories.tests.TestCaseRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
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

        TestCase testcase = null;
        if(testCaseDto.getAssignedUser() != null) {
            var assignedUserName = testCaseDto.getAssignedUser().getName();
            var assignedUser = userService.GetUserByName(assignedUserName);

            if (!assignedUser.success()) {
                return false;
            }

            new TestCase(testCaseDto.getName(), testCaseDto.getDescription(), assignedUser.object());
        }else{
            testcase = new TestCase(testCaseDto.getName(), testCaseDto.getDescription(), null);
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
                .collect(Collectors.toList());
    }

    public void updateAssignedUser(TestCaseDto testCaseDto, UserDto userDto) {
        this.testCaseRepository.findAll().stream().map(x -> Objects.equals(x.getName(), testCaseDto.getName()));
    }
}
