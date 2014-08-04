package grails.plugin.springsecurity

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured

// Overriding grails.plugin.springsecurity.LoginController or LogoutController requires rewriting them
// http://stackoverflow.com/questions/19397159/grails-spring-security-login-logout-controllers-not-generated
@Secured('permitAll')
class LogoutController {

  /**
   * Index action. Redirects to the Spring security logout uri.
   */
  def index = {
    // TODO put any pre-logout code here
    redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'
  }
}
