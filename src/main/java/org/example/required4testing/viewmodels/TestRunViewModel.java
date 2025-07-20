package org.example.required4testing.viewmodels;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.required4testing.dtos.TestCaseDto;
import org.example.required4testing.dtos.TestRunDto;
import org.example.required4testing.services.TestRunService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Named
@RequestScoped
public class TestRunViewModel {

    private String Title;

    private Collection<TestRunDto> TestRuns;

    @Inject
    private TestRunService testRunService;

    @Inject
    private UserViewModel userViewModel;

    private List<TestCaseDto> selectedTestCases;

    public void save() {
        var ctx = FacesContext.getCurrentInstance();
        var userDto = userViewModel.GetUserFromSession();
        var created = testRunService.Create(userDto, new TestRunDto(Title, null));

        if (!created) {
            ctx.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "You user level is to low",
                    null));
            ctx.validationFailed();
        }

        ctx.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Testlauf gespeichert", null));

    }

    public void updateTestRun(TestRunDto testRunDto) {
        var ctx = FacesContext.getCurrentInstance();
        var userDto = userViewModel.GetUserFromSession();

        var allTestCases = this.getSelectedTestCases();
        var updatedResults = new ArrayList<Boolean>();
        for (TestCaseDto testCase : allTestCases) {
            updatedResults.add(this.testRunService.updateTestRun(userDto, testCase, testRunDto));
        }

        if (updatedResults.stream().anyMatch(x -> x == false)) {
            ctx.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Something went wrong", null));
            ctx.validationFailed();
        }
        ctx.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Cases wurden updated", null));
    }

    public void setSelectedTestCases(List<TestCaseDto> selectedTestCases) {
        this.selectedTestCases = selectedTestCases;
    }

    public List<TestCaseDto> getSelectedTestCases() {
        return selectedTestCases;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Collection<TestRunDto> getTestRuns() {
        return this.testRunService.getAll();
    }

    public void setTestRuns(Collection<TestRunDto> testRuns) {
        TestRuns = testRuns;
    }
}
