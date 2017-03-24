package com.objectpartners.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.CredentialsContainer
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class MathAuthenticationToken implements Authentication, CredentialsContainer {

    Object credentials
    Object questionId
    Object principal
    Object details
    Collection<GrantedAuthority> authorities = []
    Boolean authenticated = false

    static final LOGGED_IN_USER_NAME = 'Fellow Scientist'

    public MathAuthenticationToken(String questionId,
                                   String guess) {
        this.questionId = questionId
        this.credentials = guess
        this.authorities << new SimpleGrantedAuthority('ROLE_USER')
    }

    @Override
    boolean isAuthenticated() {
        return authenticated
    }

    @Override
    void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        this.authenticated = authenticated
    }

    @Override
    String getName() {
        return LOGGED_IN_USER_NAME
    }

    @Override
    void eraseCredentials() {
        if (this.credentials instanceof CredentialsContainer) {
            ((CredentialsContainer)this.credentials).eraseCredentials();
        }
    }
}
