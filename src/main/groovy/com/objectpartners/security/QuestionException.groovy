package com.objectpartners.security

import org.springframework.security.core.AuthenticationException

class QuestionException extends AuthenticationException{

    QuestionException(String msg) {
        super(msg)
    }
}
