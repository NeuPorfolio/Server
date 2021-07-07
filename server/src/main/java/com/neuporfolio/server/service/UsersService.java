package com.neuporfolio.server.service;

import com.neuporfolio.server.dao.UsersRepository;
import com.neuporfolio.server.domain.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UsersService implements UserDetailsService {
    @Resource
    private UsersRepository usersRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void save(Users user) {
        if (user.getUsername() != null && user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if (usersRepository.findByUsername(user.getUsername()) == null) {
                user.setRegistrationDate(new Date());
            }
            usersRepository.save(user);
        }
    }

    @Transactional
    public void delete(Long id) {
        usersRepository.deleteById(id);
    }

    @Transactional
    public Iterable<Users> getAll() {
        return usersRepository.findAll();
    }

    @Transactional
    public Users getOne(Long id) {
        return usersRepository.findById(id).get();
    }

    @Transactional
    public Users findByUserName(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException(
                    "User'" + username + "'not found");
        }
        System.out.println(users);
        return users;
    }
}
