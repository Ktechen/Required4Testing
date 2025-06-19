package org.example.required4testing.services;

import jakarta.inject.Inject;
import org.example.required4testing.dtos.TestCaseDto;
import org.example.required4testing.dtos.TestRequirementDto;
import org.example.required4testing.dtos.UserDto;
import org.example.required4testing.repositories.tests.TestCaseRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class TestCaseService {

    @Inject
    private TestCaseRepository testCaseRepository;

    public Collection<TestCaseDto> GetAll() {
        return this.testCaseRepository.findAll().stream()
                .map(x ->
                        new TestCaseDto(
                                x.getId(),
                                x.getName(),
                                x.getDescription(),
                                new UserDto(
                                        x.getAssignedToUser().getName(),
                                        x.getAssignedToUser().getLevel())
                        ))
                .collect(Collectors.toList());
    }
}
