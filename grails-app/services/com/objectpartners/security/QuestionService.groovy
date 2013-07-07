package com.objectpartners.security

import org.springframework.transaction.annotation.Transactional

class QuestionService {

    /**
     * Retrieve a random Question instance.
     * @return
     */
    @Transactional(readOnly = true)
    def generateQuestion() {
        return Question.get(Math.abs(new Random().nextInt(Question.count()) + 1))
    }
}
