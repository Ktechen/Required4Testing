package org.example.required4testing.viewmodels;

import jakarta.faces.view.ViewScoped;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@ViewScoped
public class UserProfileViewModel implements Serializable {
    private String Name;

    public UserProfileViewModel() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
}
