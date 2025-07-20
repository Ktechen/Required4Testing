package org.example.required4testing.viewmodels;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.required4testing.dtos.TestCaseDto;
import org.example.required4testing.dtos.TestRequirementDto;
import org.example.required4testing.services.TestRequirementService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Named
@RequestScoped
public class TestRequirementViewModel {

    @Inject
    private TestRequirementService testRequirementService;

    @Inject
    private UserViewModel userViewModel;

    @Inject
    private TestCaseViewModel testCaseViewModel;

    private String Title;
    private String Description;
    private Collection<TestRequirementDto> TestRequirements;

    private List<TestCaseDto> selectedTestCases;

    public void save() {
        var ctx = FacesContext.getCurrentInstance();
        var userDto = userViewModel.GetUserFromSession();
        var testRequirementDto = new TestRequirementDto(
                getTitle(),
                getDescription(),
                null);
        var created = testRequirementService.create(userDto, testRequirementDto);

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

    public Collection<TestCaseDto> getTestCases() {
        return this.testCaseViewModel.getTestCases();
    }

    public void addTestCase() {

    }

    public TestRequirementViewModel() {
    }

    public TestRequirementViewModel(String title, String description, Collection<TestRequirementDto> testRequirements) {
        Title = title;
        Description = description;
        TestRequirements = testRequirements;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Collection<TestRequirementDto> getTestRequirements() {
        return this.testRequirementService.GetAll();
    }

    public void setTestRequirements(Collection<TestRequirementDto> testRequirements) {
        this.TestRequirements = testRequirements;
    }

    public List<TestCaseDto> getSelectedTestCases() {
        return selectedTestCases;
    }

    public void setSelectedTestCases(List<TestCaseDto> selectedTestCases) {
        this.selectedTestCases = selectedTestCases;
    }

    public void updateTestcases(TestRequirementDto requirementDto) {
        var ctx = FacesContext.getCurrentInstance();
        var userDto = userViewModel.GetUserFromSession();

        var allTestCases = this.getSelectedTestCases();
        var updatedResults = new ArrayList<Boolean>();
        for (TestCaseDto testCase : allTestCases) {
            updatedResults.add(this.testRequirementService.update(userDto, testCase, requirementDto));
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
}
