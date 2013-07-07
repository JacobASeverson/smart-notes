package com.objectpartners.security

import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ApplicationEventPublisherAware
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy
import org.springframework.web.filter.GenericFilterBean

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Part of the security filter chain for math-based authentication.
 */
class MathAuthenticationFilter extends GenericFilterBean implements ApplicationEventPublisherAware {

    String filterProcessesUrl
    AuthenticationManager authenticationManager
    SessionAuthenticationStrategy sessionAuthenticationStrategy
    AuthenticationSuccessHandler authenticationSuccessHandler
    AuthenticationFailureHandler authenticationFailureHandler
    ApplicationEventPublisher applicationEventPublisher

    /**
     * If the incoming request URI contains 'j_spring_security_math_check',
     * the filter commences math-based authentication.
     * @param req
     * @param resp
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)  throws IOException,
                                                                                      ServletException {
        HttpServletRequest request = (HttpServletRequest) req
        HttpServletResponse response = (HttpServletResponse) resp

        // If the request URI doesn't contain the filterProcessesUrl,
        // it isn't a request that should be handled by this filter
        if(!request.getRequestURI().contains(filterProcessesUrl)) {
            chain.doFilter(request, response)
            return
        }

        logger.debug('Request requires math-based authentication')

        Authentication authentication

        try {
            authentication = attemptAuthentication(request)
            if(!authentication) {
                return
            }
            sessionAuthenticationStrategy.onAuthentication(authentication, request, response)

        } catch(AuthenticationException failed) {
            unsuccessfulAuthentication(request, response, failed)
            return
        }
        successfulAuthentication(request, response, authentication)
    }

    /**
     * Builds an Authentication object and passes it to the AuthenticationManager
     * to attempt to authenticate the user.
     * @param request
     * @return
     * @throws AuthenticationException
     */
    public Authentication attemptAuthentication(HttpServletRequest request) throws AuthenticationException {
        String questionId = obtainQuestionId(request).trim()
        String guess = obtainQuestionGuess(request).trim()
        MathAuthenticationToken token = new MathAuthenticationToken(questionId,
                                                                    guess)
        return this.getAuthenticationManager().authenticate(token)
    }

    /**
     * Sets the Authentication object in the SpringSecurityContext and calls
     * the AuthenticationSuccessHandler upon successful authentication.
     * @param request
     * @param response
     * @param authentication
     */
    private void successfulAuthentication(HttpServletRequest request,
                                          HttpServletResponse response,
                                          Authentication authentication) {
        logger.debug("Successfully authenticated with math-based authentication: " + authentication)

        // When a populated Authentication object is placed in the SecurityContextHolder,
        // the user is authenticated.
        SecurityContextHolder.getContext().setAuthentication(authentication)

        applicationEventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authentication, this.getClass()))

        authenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication)
    }

    /**
     * Clears the SecurityContextHolder and calls the AuthenticationFailureHandler
     * upon unsuccessful authentication.
     * @param request
     * @param response
     * @param failed
     */
    private void unsuccessfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            AuthenticationException failed) {
        SecurityContextHolder.clearContext()
        logger.debug('Math-based authentication failed: ' + failed.toString())
        authenticationFailureHandler.onAuthenticationFailure(request,
                                                             response,
                                                             failed)
    }

    /**
     * Get Question ID from the request.
     * @param request
     * @return
     */
    private String obtainQuestionId(HttpServletRequest request) {
        return request.getParameter('questionId')
    }

    /**
     * Get the Question Guess from the request.
     * @param request
     * @return
     */
    private String obtainQuestionGuess(HttpServletRequest request) {
        return request.getParameter('questionGuess')
    }
}