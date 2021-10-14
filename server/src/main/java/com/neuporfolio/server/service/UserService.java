package com.neuporfolio.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuporfolio.server.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 */

public interface UserService extends IService<User>, UserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    void setRegistrationStatus(String username, boolean isRegFinished) throws UsernameNotFoundException;

    User buildUser(String username, String password, String role) throws ExistedUserException, UnavailableRoleException, DaoFactException;

    class ExistedUserException extends Exception {

    }

    class UnavailableRoleException extends Exception {

    }

    class DaoFactException extends Exception {

    }
}
