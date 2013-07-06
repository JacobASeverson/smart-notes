package com.objectpartners.security

class Question {

    String questionDisplay
    String answer

    static constraints = {
        questionDisplay nullable: false, unique: true
        answer nullable: false
    }
}
