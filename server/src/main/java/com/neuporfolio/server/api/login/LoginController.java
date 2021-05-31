package com.neuporfolio.server.api.login;

import com.neuporfolio.server.security.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path="/api/login")
public class LoginController {

    @PostMapping
    public LoginResponseForm login(LoginRequestForm form) {
        User u = form.toUser();

        return null;
    }
}
