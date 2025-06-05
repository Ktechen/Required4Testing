package org.example.required4testing.viewmodels;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.required4testing.services.LoginService;

@Named
@RequestScoped
public class LoginViewModel {
    private String username;
    private String password;

    @Inject
    private LoginService loginService;

    public String login() {
        if (loginService.validate(username, password)) {
            return "home.xhtml?faces-redirect=true";
        } else {
            return "redirect:/index.xhtml";
        }
    }
}
