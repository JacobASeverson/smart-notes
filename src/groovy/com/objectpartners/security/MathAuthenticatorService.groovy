package com.objectpartners.security

class MathAuthenticatorService {

    /**
     * Compare a guess to the stored answer of the question.
     * @param questionId
     * @param guess
     */
    public void checkGuess(String questionId, String guess) {
        Question question = Question.get(questionId)
        if(!question.answer.equals(guess)) {
            throw new QuestionException('Incorrect Guess.')
        }
    }
}
