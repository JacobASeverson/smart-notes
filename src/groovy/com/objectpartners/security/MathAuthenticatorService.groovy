package com.objectpartners.security

class MathAuthenticatorService {

    public void checkGuess(String questionId,
                           String guess) {
        Question.withTransaction {
            Question question = Question.get(questionId)
            if(!question.answer.equals(guess)) {
                throw new QuestionException('Sorry, that is an incorrect guess.')
            }
        }
    }
}
