package com.objectpartners.security

import org.springframework.web.filter.GenericFilterBean

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

/**
 *
 */
class MathAuthenticationFilter extends GenericFilterBean {
    @Override
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
                                                                                              ServletException {

    }
}
