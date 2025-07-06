package org.example.required4testing.viewmodels;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.required4testing.dtos.TestCaseDto;
import org.example.required4testing.dtos.TestResultDto;
import org.example.required4testing.dtos.TestRunDto;
import org.example.required4testing.services.TestRunService;

import java.util.Collection;

@Named
@RequestScoped
public class TestRunViewModel {
    private String StartFrom;
    private Collection<TestResultDto> TestResults;
    private Collection<TestCaseDto> testCases;

    @Inject
    private TestRunService testRunService;

    @Inject
    private LoginViewModel loginViewModel;

    public void save() {
        var ctx = FacesContext.getCurrentInstance();
        var userDto = loginViewModel.GetUserFromSession();
        var created = testRunService.Create(userDto, new TestRunDto(StartFrom, TestResults, testCases));

        if (!created) {
            ctx.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "You user level is to low",
                    null));
            ctx.validationFailed();
        }

        ctx.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Anforderung gespeichert", null));
    }

    public String getStartFrom() {
        return StartFrom;
    }

    public void setStartFrom(String startFrom) {
        StartFrom = startFrom;
    }

    public Collection<TestResultDto> getTestResults() {
        return TestResults;
    }

    public void setTestResults(Collection<TestResultDto> testResults) {
        TestResults = testResults;
    }

    public Collection<TestCaseDto> getTestCases() {
        return testCases;
    }

    public void setTestCases(Collection<TestCaseDto> testCases) {
        this.testCases = testCases;
    }
}
