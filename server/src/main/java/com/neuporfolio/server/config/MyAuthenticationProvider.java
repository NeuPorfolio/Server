package com.neuporfolio.server.config;

import com.neuporfolio.server.Utils.PasswordUtils;
import com.neuporfolio.server.domain.User;
import com.neuporfolio.server.service.UserService;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Resource;

/***
 * Spring security账号验证器实现
 */

public class MyAuthenticationProvider implements AuthenticationProvider {

    static private final String tempLoginCredFlagParameter = "TempCredentials";
    static private final String tempLoginCredUsernameParameter = "username";
    static private final String tempLoginCredPasswordParameter = "password";
    @Resource
    UserService userService;
    @Resource
    PasswordUtils passwordUtils;

    public static String getTempLoginCredFlagParameter() {
        return MyAuthenticationProvider.tempLoginCredFlagParameter;
    }

    public static String getTempLoginCredUsernameParameter() {
        return MyAuthenticationProvider.tempLoginCredUsernameParameter;
    }

    public static String getTempLoginCredPasswordParameter() {
        return MyAuthenticationProvider.tempLoginCredPasswordParameter;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        User user = (User) userService.loadUserByUsername(username);

        /*
        密码匹配失败
         */
        boolean isValid = passwordUtils.isValid(user.getPassword(), password);
        if (!isValid) {
            throw new BadCredentialsException("Received a wrong password");
        }

        /*
        注册未完成，不予以登录
         */
        if (!user.getIsRegFinished()) {
            throw new RegistrationNotFinishedException("Registration is not finished", user.getRole(), username, password);
        }
        UserDetails userDetails = (UserDetails) user;
        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

    public static class RegistrationNotFinishedException extends AccountStatusException {
        public int code = 200;
        public String identity;
        public String username;
        public String password;

        public RegistrationNotFinishedException(String msg, String identity, String username, String password) {
            super(msg);
            this.identity = identity;
            this.username = username;
            this.password = password;
        }
    }
}
