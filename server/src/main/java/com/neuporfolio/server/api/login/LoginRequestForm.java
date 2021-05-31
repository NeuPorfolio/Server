package com.neuporfolio.server.api.login;

import com.neuporfolio.server.security.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class LoginRequestForm {
    private String username;
    private String password;

    public User toUser() {
        return new User(username, password);
    }
}
