package com.objectpartners.security

class Role {

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority nullable: false, unique: true
	}
}
