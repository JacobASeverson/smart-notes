package com.objectpartners.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException

class MathAuthenticationProvider implements AuthenticationProvider {

    @Override
    Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    boolean supports(Class<? extends Object> aClass) {
        return false  //To change body of implemented methods use File | Settings | File Templates.
    }
}
