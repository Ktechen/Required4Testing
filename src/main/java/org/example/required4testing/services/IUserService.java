package org.example.required4testing.services;

import org.example.required4testing.dtos.TestRequirementDto;
import org.example.required4testing.dtos.UserDto;

public interface IUserService {
    /**
     * Creates new test requirements.
     *
     * @param requirement Data Transfer Object (DTO) containing the test requirement details.
     * @return true if the test requirement was created successfully, false otherwise.
     */
    boolean CreateTestsRequirements(final UserDto userDto, final TestRequirementDto requirement);

    /**
     * Save new user
     *
     * @param userDto Data Transfer Object (DTO) containing the user details.
     */
    void SaveUser(UserDto userDto);
}
