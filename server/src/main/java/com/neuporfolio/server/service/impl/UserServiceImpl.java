package com.neuporfolio.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuporfolio.server.domain.User;
import com.neuporfolio.server.mapper.UserMapper;
import com.neuporfolio.server.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService, UserDetailsService {
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userMapper.selectAllByUsername(username).get(0);
    }

    @Override
    public void setRegistrationStatus(String username, boolean isRegFinished) throws UsernameNotFoundException {
        userMapper.setIsRegFinishedByUsername(isRegFinished, username);
    }

    @Override
    public User buildUser(String username, String password, String role) throws ExistedUserException, UnavailableRoleException, DaoFactException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        userMapper.insertAll(user);
        return user;
    }
}




