package org.example.required4testing.viewmodels;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.required4testing.services.LoginService;

import java.io.IOException;

@Named
@RequestScoped
public class LoginViewModel {
    private String username;
    private String password;

    @Inject
    private LoginService loginService;

    public void login() throws IOException {
        var ctx = FacesContext.getCurrentInstance().getExternalContext();
        if (loginService.validate(username, password)) {
            ctx.redirect(ctx.getRequestContextPath() + "/tests/overview.xhtml");
            ;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Ungültiger Benutzername oder Passwort",
                    null));
            FacesContext.getCurrentInstance().validationFailed();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
