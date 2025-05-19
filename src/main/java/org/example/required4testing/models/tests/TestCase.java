package org.example.required4testing.models.tests;

import jakarta.persistence.*;
import org.example.required4testing.models.BaseEntity;

import java.util.List;

@Entity
@Table
public class TestCase extends BaseEntity {
    private String name;
    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn()
    private List<TestRun> testRuns;
}
