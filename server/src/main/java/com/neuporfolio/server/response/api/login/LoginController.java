package com.neuporfolio.server.response.api.login;

import com.neuporfolio.server.Security.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/login")
public class LoginController {

    @PostMapping
    public User login(User u)
    {
        return u;
    }
}
