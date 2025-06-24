package org.example.required4testing.viewmodels;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.required4testing.models.UserLevelType;

@Named
@RequestScoped
public class NavbarViewModel {

    @Inject
    private LoginViewModel loginViewModel;

    public String navigateToTestRequirement() {
        if (loginViewModel.GetUserFromSession().getLevel() >= UserLevelType.RequirementsEngineer.getValue()) {
            return "/tests/testrequirement.xhtml";
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Access Denied", String.format("Level %s required.", UserLevelType.RequirementsEngineer.name())));
        return null; // Stay on page
    }

    public String navigateToTestResult() {
        if (loginViewModel.GetUserFromSession().getLevel() >= UserLevelType.Testmanager.getValue()) {
            return "/tests/testresult.xhtml";
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Access Denied", String.format("Level %s required.", UserLevelType.Testmanager.name())));
        return null;
    }

    public String navigateToTestRun() {
        if (loginViewModel.GetUserFromSession().getLevel() >= UserLevelType.Tester.getValue()) {
            return "/tests/testrun.xhtml";
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Access Denied", String.format("Level %s required.", UserLevelType.Tester.name())));
        return null;
    }

}
