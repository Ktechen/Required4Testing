package org.example.required4testing.viewmodels;

import jakarta.el.MethodExpression;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.required4testing.dtos.TestCaseDto;
import org.example.required4testing.dtos.UserDto;
import org.example.required4testing.services.TestCaseService;
import org.example.required4testing.services.UserService;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class TestCaseViewModel {
    private UUID id;
    private String name;
    private String description;
    private UserDto assignedUser;

    @Inject
    private UserService userService;

    @Inject
    private TestCaseService testCaseService;

    @Inject
    private LoginViewModel loginViewModel;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDto getAssignedUser() {
        return assignedUser;
    }

    public Collection<UserDto> getAllUser() {
        return userService.getAll().stream()
                .map(x -> new UserDto(x.getName(), x.getLevel()))
                .collect(Collectors.toList());
    }

    public void setAssignedUser(UserDto assignedUser) {
        this.assignedUser = assignedUser;
    }

    public void save() {
        var ctx = FacesContext.getCurrentInstance();
        var userDto = loginViewModel.GetUserFromSession();
        var created = testCaseService.create(userDto, new TestCaseDto(name, description, assignedUser));
        if (!created) {
            ctx.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Something went wrong",
                    null));
            ctx.validationFailed();
        }
        ctx.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Testcase gespeichert", null));
    }
}
