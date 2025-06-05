package org.example.required4testing.services;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public boolean validate(String username, String password) {
        // Dummy Logik, z. B. DB-Check
        return "admin".equals(username) && "1234".equals(password);
    }

}
