package org.example.required4testing.viewmodels;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.required4testing.dtos.TestRequirementDto;
import org.example.required4testing.dtos.UserDto;
import org.example.required4testing.services.TestRequirementService;

import java.util.Collection;

@Named
@RequestScoped
public class TestRequirementViewModel {

    @Inject
    private TestRequirementService testRequirementService;

    private String Username;

    private String Level;

    private String Title;
    private String Description;
    private Collection<TestRequirementDto> TestRequirements;

    public void save() {
        var ctx = FacesContext.getCurrentInstance();
        var username = String.valueOf(ctx.getExternalContext()
                .getSessionMap()
                .get("username"));

        var level = Integer.parseInt(
                String.valueOf(ctx.getExternalContext()
                        .getSessionMap()
                        .get("level")));

        var userDto = new UserDto(username, level);
        var testRequirementDto = new TestRequirementDto(
                getTitle(),
                getDescription(),
                null);
        var created = testRequirementService.CreateTestsRequirements(userDto, testRequirementDto);

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

    public TestRequirementViewModel() {
    }

    public TestRequirementViewModel(String username, String level, String title, String description, Collection<TestRequirementDto> testRequirements) {
        Username = username;
        Level = level;
        Title = title;
        Description = description;
        TestRequirements = testRequirements;
    }

    public String getUsername() {
        return (String) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .get("username");
    }

    public String getLevel() {
        return FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .get("level").toString();
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
}
