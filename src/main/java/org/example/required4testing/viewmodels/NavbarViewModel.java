package org.example.required4testing.viewmodels;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.required4testing.models.UserLevelType;

import java.io.IOException;

@Named
@RequestScoped
public class NavbarViewModel {

    @Inject
    private LoginViewModel loginViewModel;

    public String navigateToTestRequirement() throws IOException {
        return navigateIfAuthorized(UserLevelType.RequirementsEngineer, "/tests/testrequirement.xhtml");
    }

    public String navigateToTestResult() throws IOException {
        return navigateIfAuthorized(UserLevelType.Testfallersteller, "/tests/testresult.xhtml");
    }

    public String navigateToTestRun() throws IOException {
        return navigateIfAuthorized(UserLevelType.Tester, "/tests/testrun.xhtml");
    }

    public String navigateToTestCase() throws IOException {
        return navigateIfAuthorized(UserLevelType.Testmanager, "/tests/testcase.xhtml");
    }

    private String navigateIfAuthorized(UserLevelType requiredLevel, String path) throws IOException {
        var ctx = FacesContext.getCurrentInstance();
        int userLevel = loginViewModel.GetUserFromSession().getLevel();

        if (userLevel >= requiredLevel.getValue()) {
            ctx.getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + path);
            return path;
        }

        ctx.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Access Denied",
                        String.format("Level %s required.", requiredLevel.name())));
        ctx.validationFailed();
        return null;
    }
}
