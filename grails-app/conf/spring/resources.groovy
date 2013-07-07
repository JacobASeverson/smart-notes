import com.objectpartners.security.MathAuthenticationFilter
import com.objectpartners.security.MathAuthenticationProvider
import com.objectpartners.security.MathAuthenticatorService
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy

// Place your Spring DSL code here
beans = {

    mathAuthenticationFilter(MathAuthenticationFilter) {
        filterProcessesUrl = 'j_spring_security_math_check'
        authenticationSuccessHandler = ref('authenticationSuccessHandler')
        authenticationFailureHandler = ref('authenticationFailureHandler')
        authenticationManager = ref('authenticationManager')
        sessionAuthenticationStrategy = ref('sessionAuthenticationStrategy')
    }

    mathAuthenticationProvider(MathAuthenticationProvider) {
        mathAuthenticatorService = ref('mathAuthenticatorService')
    }

    mathAuthenticatorService(MathAuthenticatorService)

    sessionAuthenticationStrategy(NullAuthenticatedSessionStrategy)
}
