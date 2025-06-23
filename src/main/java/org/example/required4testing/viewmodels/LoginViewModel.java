package org.example.required4testing.viewmodels;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.required4testing.services.UserService;

import java.io.IOException;
import java.util.HashMap;

@Named
@RequestScoped
public class LoginViewModel {
    private String username;
    private String password;

    @Inject
    private UserService userService;

    public void login() throws IOException {
        var ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().getSessionMap().clear();

        var userValidation = userService.validate(username, password);
        if (userValidation.success()) {
            var map =  new HashMap<String, Object>();
            map.put("username", userValidation.object().getName());
            map.put("level", String.valueOf(userValidation.object().getLevel()));
            ctx.getExternalContext().getSessionMap().putAll(map);
            ctx.getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + "/tests/testrequirement.xhtml");
        } else {
            ctx.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Ungültiger Benutzername oder Passwort",
                    null));
            ctx.validationFailed();
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
