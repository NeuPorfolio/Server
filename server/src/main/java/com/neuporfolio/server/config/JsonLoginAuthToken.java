package com.neuporfolio.server.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JsonLoginAuthToken extends AbstractAuthenticationToken {

    private final Object principal;

    private final Object credentials;

    public JsonLoginAuthToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
