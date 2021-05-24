package com.neuporfolio.server.response.api.login;

import com.neuporfolio.server.Security.User;
import lombok.Data;

@Data
public class LoginForm {
    private String username;
    private String password;
    public User toUser()
    {
        return new User(username,password);
    }
}
