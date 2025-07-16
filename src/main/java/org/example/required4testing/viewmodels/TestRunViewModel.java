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

import java.util.ArrayList;
import java.util.Collection;

@Named
@RequestScoped
public class TestRunViewModel {

    private String Title;

    private Collection<TestRunDto> TestRuns;

    @Inject
    private TestRunService testRunService;

    @Inject
    private LoginViewModel loginViewModel;

    public void save() {
        var ctx = FacesContext.getCurrentInstance();
        var userDto = loginViewModel.GetUserFromSession();
        var created = testRunService.Create(userDto, new TestRunDto(Title, null, null));

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
