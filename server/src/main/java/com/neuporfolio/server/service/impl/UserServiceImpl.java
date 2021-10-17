package com.neuporfolio.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuporfolio.server.domain.Identity;
import com.neuporfolio.server.domain.User;
import com.neuporfolio.server.mapper.IdentityMapper;
import com.neuporfolio.server.mapper.UserMapper;
import com.neuporfolio.server.service.UserService;
import com.neuporfolio.server.utils.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 *
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService, UserDetailsService {

    @Resource
    UserMapper userMapper;

    @Resource
    IdentityMapper identityMapper;

    @Resource
    PasswordUtils passwordUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("username:" + username);
        User user = userMapper.selectOneByUsername(username);
        log.debug("userDetails.username:" + user.getUsername());
        return user;
    }

    @Override
    public void setRegistrationStatus(String username, boolean isRegFinished) throws UsernameNotFoundException {
        userMapper.setIsRegFinishedByUsername(isRegFinished, username);
    }

    @Override
    public User buildUser(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordUtils.encode(password));
        Identity identity = identityMapper.selectOneByAllStyleRoleName(role);
        if (identity != null)
            role = identity.getRole();
        user.setRole(role);
        user.setRegistrationDate(new Date());
        user.setIsRegFinished(false);
        userMapper.insertAll(user);
        return user;
    }
}




