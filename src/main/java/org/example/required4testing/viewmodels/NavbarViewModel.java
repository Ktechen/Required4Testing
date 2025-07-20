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
    private UserViewModel userViewModel;

    public void navigateToOverview() throws IOException {
        var ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + "/overview.xhtml");
    }

    public String navigateToTestRequirement() throws IOException {
        return navigateIfAuthorized(UserLevelType.RequirementsEngineer, "/tests/testrequirement.xhtml");
    }

    public String navigateToTestRun() throws IOException {
        return navigateIfAuthorized(UserLevelType.Tester, "/tests/testrun.xhtml");
    }

    public String navigateToTestCase() throws IOException {
        return navigateIfAuthorized(UserLevelType.Testmanager, "/tests/testcase.xhtml");
    }

    public String navigateToMyTestCases() throws IOException {
        return navigateIfAuthorized(UserLevelType.Tester, "/me/testcases.xhtml");
    }

    private String navigateIfAuthorized(UserLevelType requiredLevel, String path) throws IOException {
        var ctx = FacesContext.getCurrentInstance();
        int userLevel = userViewModel.GetUserFromSession().getLevel();

        if (userLevel >= requiredLevel.getValue()) {
            ctx.getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + path);
            return path;
        }

        ctx.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Access Denied",
                        String.format("Level %s required.", requiredLevel.name()) + " Your level is " + UserLevelType.fromLevel(userLevel).name())
        );
        ctx.validationFailed();
        return null;
    }
}
