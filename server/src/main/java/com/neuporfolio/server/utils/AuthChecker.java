package com.neuporfolio.server.utils;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class AuthChecker {

    public boolean isAdminAuth(Authentication auth) {
        if (auth instanceof AnonymousAuthenticationToken)
            return false;
        return auth.getAuthorities().contains(new SimpleGrantedAuthority(ConstValues.roleAdminParameter));
    }

    public boolean isStuAuth(Authentication auth) {
        if (auth instanceof AnonymousAuthenticationToken)
            return false;
        return auth.getAuthorities().contains(new SimpleGrantedAuthority(ConstValues.roleStudentParameter));
    }

    public boolean isTeaAuth(Authentication auth) {
        if (auth instanceof AnonymousAuthenticationToken)
            return false;
        return auth.getAuthorities().contains(new SimpleGrantedAuthority(ConstValues.roleTeacherParameter));
    }
}
