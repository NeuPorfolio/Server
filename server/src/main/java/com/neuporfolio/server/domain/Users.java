package com.neuporfolio.server.domain;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Users implements UserDetails {
    //要求属性名和数据库命名一致
    @Id//定义主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//使用IDENTITY主键生成策略
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private boolean enabled;
    private String role;
    private Date registrationDate;
    private Date lastLoginDate;
    private String email;
    private String phone;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = true;
    }

    public Users(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = true;
    }

    public void copyUsers(Users toUser) {
        this.username = toUser.getUsername();
        this.password = toUser.getPassword();
        this.role = toUser.getRole();
        this.email = toUser.getEmail();
        this.phone = toUser.getPhone();

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
