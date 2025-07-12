package org.example.required4testing.viewmodels;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.required4testing.dtos.TestCaseDto;
import org.example.required4testing.dtos.TestRequirementDto;
import org.example.required4testing.dtos.UserDto;
import org.example.required4testing.models.User;
import org.example.required4testing.repositories.tests.TestCaseRepository;
import org.example.required4testing.services.TestCaseService;
import org.example.required4testing.services.TestRequirementService;
import org.example.required4testing.services.UserService;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class TestCaseViewModel {
    private UUID id;
    private String name;
    private String description;
    private String assignedUserName;
    private Collection<TestCaseDto> testCases;

    @Inject
    private UserService userService;

    @Inject
    private TestCaseService testCaseService;

    @Inject
    private LoginViewModel loginViewModel;

    @Inject
    private TestRequirementService testRequirementService;

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

    public String getAssignedUserName() {
        return assignedUserName;
    }

    public void setAssignedUserName(String assignedUserName) {
        this.assignedUserName = assignedUserName;
    }

    public void setTestCases(Collection<TestCaseDto> testCases) {
        this.testCases = testCases;
    }

    public Collection<TestCaseDto> getTestCases() {
        return testCaseService.GetAll().stream()
                .map(x -> new TestCaseDto(
                        x.getName(),
                        x.getDescription(),
                        x.getAssignedUser() != null
                                ? x.getAssignedUser()
                                : new UserDto(null, "Not Set", -1)
                ))
                .collect(Collectors.toList());
    }


    public List<String> searchUserNames(String query) {
        var users = userService.getAll().stream()
                .map(User::getName)
                .filter(name -> name.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());

        users.stream().findFirst().ifPresent(this::setAssignedUserName);

        return users;
    }

    public List<String> searchTestRequirement(String query) {
        return testCaseService.GetAll().stream().map(TestCaseDto::getName).filter(x -> name.toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
    }

    public void save() {
        var ctx = FacesContext.getCurrentInstance();
        var userDto = loginViewModel.GetUserFromSession();

        var assignedUser = userService.getAll().stream()
                .filter(u -> u.getName().equalsIgnoreCase(assignedUserName))
                .findFirst()
                .map(u -> new UserDto(u.getId(), u.getName(), u.getLevel()))
                .orElse(null);

        var created = testCaseService.create(userDto, new TestCaseDto(name, description, assignedUser));

        if (!created) {
            ctx.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Something went wrong", null));
            ctx.validationFailed();
            return;
        }

        ctx.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Testcase gespeichert", null));
    }

    public void updateAssignedUser(TestCaseDto testCaseDto) {
        var ctx = FacesContext.getCurrentInstance();
        var user = userService.GetUserByName(assignedUserName);
        if (!user.success()) {
            ctx.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "User not found", null));
            ctx.validationFailed();
            return;
        }
        testCaseService.updateAssignedUser(testCaseDto, user.object());
    }

    public void updateRequirements(TestCaseDto testCaseDto) {
        var ctx = FacesContext.getCurrentInstance();
        var user = userService.GetUserByName(assignedUserName);
        if (!user.success()) {
            ctx.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "User not found", null));
            ctx.validationFailed();
            return;
        }
        var userDto = new UserDto(user.object().getId(), user.object().getName(), user.object().getLevel());
        testRequirementService.update(userDto, testCaseDto, );

    }
}

