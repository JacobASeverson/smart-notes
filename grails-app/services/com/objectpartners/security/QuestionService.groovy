package com.objectpartners.security

import org.springframework.transaction.annotation.Transactional

class QuestionService {

    @Transactional(readOnly = true)
    def generateQuestion() {
        Integer questionTotal = Question.count()
        return Question.get(Math.abs(new Random().nextInt() % questionTotal + 1))
    }
}
