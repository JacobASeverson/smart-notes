package com.objectpartners.security

import org.springframework.context.MessageSource
import org.springframework.context.MessageSourceAware
import org.springframework.context.support.MessageSourceAccessor
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.SpringSecurityMessageSource
import org.springframework.util.Assert

class MathAuthenticationProvider implements AuthenticationProvider,
                                            MessageSourceAware{

    MathAuthenticatorService mathAuthenticatorService
    MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor()

    /**
     * Authenticates the application user and returns a populated
     * Authentication object if successful.
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    Authentication authenticate(Authentication auth) throws AuthenticationException {

        Assert.isInstanceOf(MathAuthenticationToken.class, auth,
                messages.getMessage("MathAuthenticationProvider.onlySupports",
                        "Only MathAuthenticationToken is supported"))

        MathAuthenticationToken authentication = (MathAuthenticationToken) auth
        String questionId = authentication.questionId
        String guess = authentication.credentials

        try {

            mathAuthenticatorService.checkGuess(questionId, guess)

        } catch (AuthenticationException authenticationException) {

            throw authenticationException
        }
        return createSuccessfulAuthentication(authentication)
    }

    private Authentication createSuccessfulAuthentication(Authentication authentication) {
        authentication.setAuthenticated(true)
        return authentication
    }

    /**
     * Tests whether this provider supports the Authentication
     * type being passed in.
     * @param authentication
     * @return
     */
    @Override
    boolean supports(Class<? extends Object> authentication) {
        return MathAuthenticationToken.class.isAssignableFrom(authentication)
    }


    @Override
    void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource)
    }
}
