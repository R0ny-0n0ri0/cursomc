package com.nelioalves.cursomc.serveces;

import org.springframework.security.core.context.SecurityContextHolder;

import com.nelioalves.cursomc.security.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
		return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}

}
