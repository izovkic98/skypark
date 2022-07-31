package com.tvz.skypark.security.jwt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import com.tvz.skypark.security.UserPrinciple;

public interface JwtProvider {

	String generateToken(UserPrinciple auth);

	boolean isTokenValid(HttpServletRequest request);

	Authentication getAuthentication(HttpServletRequest request);

}
