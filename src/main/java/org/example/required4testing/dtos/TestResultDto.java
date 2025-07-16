package org.example.required4testing.dtos;

import org.example.required4testing.models.tests.TestResultType;

import java.util.UUID;

public class TestResultDto {

    private UUID Id;

    public TestResultDto() {
    }

    public TestResultDto(UUID id) {
        Id = id;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }
}
