package org.example.required4testing.services;

import jakarta.inject.Inject;
import org.example.required4testing.dtos.TestRequirementDto;
import org.example.required4testing.dtos.UserDto;
import org.example.required4testing.models.UserLevelType;
import org.example.required4testing.models.tests.TestRequirement;
import org.example.required4testing.repositories.tests.TestRequirementRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Inject
    private TestRequirementRepository testRequirementRepository;

    @Override
    public boolean CreateTestsRequirements(UserDto userDto, TestRequirementDto requirement) {
        if(userDto.getUserLevelType().getValue() == UserLevelType.RequirementsEngineer.getValue()){
            return false;
        }

        testRequirementRepository.save(new TestRequirement(
                requirement.getTitle(),
                requirement.getDescription(),
                requirement.getTestCase()
        ));
        return true;
    }

    @Override
    public void SaveUser(UserDto userDto) {

    }
}
