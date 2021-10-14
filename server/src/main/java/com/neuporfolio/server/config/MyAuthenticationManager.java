package com.neuporfolio.server.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


/***
 * Spring Security账号验证器配置
 */
public class MyAuthenticationManager implements AuthenticationManager {
    private final MyAuthenticationProvider myAuthenticationProvider;

    MyAuthenticationManager(MyAuthenticationProvider myAuthenticationProvider) {
        this.myAuthenticationProvider = myAuthenticationProvider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        return myAuthenticationProvider.authenticate(authentication);
    }
}
