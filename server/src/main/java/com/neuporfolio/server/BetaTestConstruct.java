package com.neuporfolio.server;

import com.neuporfolio.server.domain.Users;
import com.neuporfolio.server.service.UsersService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class BetaTestConstruct implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private UsersService usersService;

    public void addTestAccount() {
        if (usersService.findByUserName("beta_test") != null)
            return;
        Users beta_test = new Users("beta_test", "beta_test", "ROLE_super");
        System.out.println("addTestAccount" + beta_test);
        usersService.save(beta_test);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        addTestAccount();
    }
}
