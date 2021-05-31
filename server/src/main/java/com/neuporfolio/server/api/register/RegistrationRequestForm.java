package com.neuporfolio.server.api.register;

import com.neuporfolio.server.security.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Slf4j
public class RegistrationRequestForm {
    private String username;
    private String password;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                username, password
        );
    }
}
