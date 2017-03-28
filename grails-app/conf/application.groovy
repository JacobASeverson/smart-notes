// Spring security properties:
grails.plugin.springsecurity.authority.className = 'com.objectpartners.security.Role'
grails.plugin.springsecurity.auth.loginFormUrl = '/login/mathLogin'
grails.plugin.springsecurity.providerNames = ['mathAuthenticationProvider']

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',                              access: ['ROLE_USER']],
	[pattern: '/index',                         access: ['ROLE_USER']],
	[pattern: '/index.gsp',                     access: ['ROLE_USER']],
	[pattern: '/**/js/**',                      access: ['permitAll']],
	[pattern: '/assets/**',                     access: ['permitAll']]
]