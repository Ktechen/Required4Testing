package org.example.required4testing.viewmodels;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.required4testing.dtos.TestCaseDto;
import org.example.required4testing.dtos.UserDto;
import org.example.required4testing.models.UserLevelType;
import org.example.required4testing.services.TestCaseService;
import org.example.required4testing.services.UserService;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

@Named
@RequestScoped
public class UserViewModel {
    private String username;
    private String password;
    private String level;

    @Inject
    private UserService userService;

    @Inject
    private TestCaseService testCaseService;

    public void login() throws IOException {
        var ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().getSessionMap().clear();

        var userValidation = userService.validate(username, password);
        if (userValidation.success()) {
            var map = new HashMap<String, Object>();
            map.put("username", userValidation.object().getName());
            map.put("level", String.valueOf(userValidation.object().getLevel()));
            ctx.getExternalContext().getSessionMap().putAll(map);
            ctx.getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + "/overview.xhtml");
        } else {
            ctx.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Ungültiger Benutzername oder Passwort",
                    null));
            ctx.validationFailed();
        }
    }

    public UserDto GetUserFromSession() {
        var ctx = FacesContext.getCurrentInstance();
        var username = String.valueOf(ctx.getExternalContext()
                .getSessionMap()
                .get("username"));

        var level = Integer.parseInt(
                String.valueOf(ctx.getExternalContext()
                        .getSessionMap()
                        .get("level")));

        return new UserDto(null, username, level);
    }

    public String getUsername() {
        return (String) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .get("username");
    }

    public String getLevel() {
        int level = Integer.parseInt(
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getSessionMap()
                        .get("level")
                        .toString()
        );
        return UserLevelType.fromLevel(level).name();
    }

    public Collection<TestCaseDto> getMyTestCases() {
        var user = this.GetUserFromSession();
        return this.testCaseService.getFilteredByUser(user);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
