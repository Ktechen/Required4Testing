package org.example.required4testing.viewmodels;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.required4testing.dtos.TestRequirementDto;
import org.example.required4testing.dtos.UserDto;
import org.example.required4testing.models.tests.TestCase;
import org.example.required4testing.services.TestRequirementService;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
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
    private Collection<TestCase> TestCase;

    public void save() {
        var ctx = FacesContext.getCurrentInstance();
        var created = testRequirementService.CreateTestsRequirements(
                new UserDto(),
                new TestRequirementDto(
                        getTitle(),
                        getDescription(),
                        getTestCase()));

        if (!created) {
            ctx.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Check you data something went wrong",
                    null));
            ctx.validationFailed();
        }

        ctx.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Anforderung gespeichert", null));
    }

    public TestRequirementViewModel() {
    }

    public TestRequirementViewModel(String title, String description, Collection<TestCase> testCase) {
        Title = title;
        Description = description;
        TestCase = testCase;
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

    public Collection<TestCase> getTestCase() {
        return TestCase;
    }

    public void setTestCase(Collection<TestCase> testCase) {
        TestCase = testCase;
    }
}
