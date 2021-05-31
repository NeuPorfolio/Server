package com.neuporfolio.server.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Data
@NoArgsConstructor
@Entity
public class User implements UserDetails {
    //要求属性名和数据库命名一致
    @Id//定义主键
    @GeneratedValue(strategy = GenerationType.AUTO)//使用默认主键生成策略
    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private String role;
    private Date registration_date;
    private Date last_login_date;
    private String email;
    private String phone;

    public User(String username, String password) {
        this.id = getId();
        this.username = password;
        this.password = password;
        this.enabled = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
