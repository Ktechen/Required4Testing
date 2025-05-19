package org.example.required4testing.models.tests;

import jakarta.persistence.*;

@Entity
@Table
public class TestResultType {

    @Id
    private int Id;

    @Column(nullable = false)
    private String Option;

    public TestResultType() {
    }

    public TestResultType(int id, String option) {
        this.Id = id;
        this.Option = option;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getOption() {
        return Option;
    }

    public void setOption(String option) {
        Option = option;
    }
}
