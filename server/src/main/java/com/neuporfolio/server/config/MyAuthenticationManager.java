package com.neuporfolio.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


/***
 * Spring Security账号验证器配置
 */
@Slf4j
public class MyAuthenticationManager implements AuthenticationManager {
    private final MyAuthenticationProvider myAuthenticationProvider;

    MyAuthenticationManager(MyAuthenticationProvider myAuthenticationProvider) {
        this.myAuthenticationProvider = myAuthenticationProvider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("In MyAuthenticationManager.");
        return myAuthenticationProvider.authenticate(authentication);
    }
}
