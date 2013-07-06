import com.objectpartners.security.Question
import com.objectpartners.security.Role
import org.codehaus.groovy.grails.plugins.springsecurity.SecurityFilterPosition
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class BootStrap {

    def init = { servletContext ->
        new Role(authority: 'ROLE_USER').save(flush: true, failOnError: true)
        new Question(questionDisplay: 'What is 3 + 3?', answer: '6').save(flush: true, failOnError: true)
        new Question(questionDisplay: 'What is 4 + 5?', answer: '9').save(flush: true, failOnError: true)
        new Question(questionDisplay: 'What is 2 + 7?', answer: '9').save(flush: true, failOnError: true)
        new Question(questionDisplay: 'What is 1 + 6?', answer: '7').save(flush: true, failOnError: true)

        SpringSecurityUtils.clientRegisterFilter('mathAuthenticationFilter',
                                                  SecurityFilterPosition.SECURITY_CONTEXT_FILTER.order + 10)

    }
    def destroy = {
    }
}
