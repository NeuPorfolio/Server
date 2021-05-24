package com.neuporfolio.server.response.api.register;

import com.neuporfolio.server.Security.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;

    public User toUser(PasswordEncoder passwordEncoder){
        return new User(
                username,password
        );
    }
}
