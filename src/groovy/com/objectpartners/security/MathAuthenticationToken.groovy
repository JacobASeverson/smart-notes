package com.objectpartners.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.CredentialsContainer
import org.springframework.security.core.GrantedAuthority

class MathAuthenticationToken implements Authentication, CredentialsContainer {

    @Override
    Collection<GrantedAuthority> getAuthorities() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Object getCredentials() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Object getDetails() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Object getPrincipal() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    boolean isAuthenticated() {
        return false  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void setAuthenticated(boolean b) throws IllegalArgumentException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    String getName() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void eraseCredentials() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
