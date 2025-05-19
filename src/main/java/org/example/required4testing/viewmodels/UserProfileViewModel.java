package org.example.required4testing.viewmodels;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.required4testing.dtos.UserDto;
import org.example.required4testing.services.IUserService;

import java.io.Serializable;

@Named
@ViewScoped
public class UserProfileViewModel implements Serializable {
    private String Name;

    @Inject
    private IUserService userService;

    public UserProfileViewModel() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void save() {
        this.userService.SaveUser(new UserDto());
    }
}
