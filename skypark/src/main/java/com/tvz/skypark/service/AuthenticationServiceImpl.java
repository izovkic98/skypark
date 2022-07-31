package com.tvz.skypark.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.tvz.skypark.model.User;
import com.tvz.skypark.security.UserPrinciple;
import com.tvz.skypark.security.jwt.JwtProvider;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;
	
    @Autowired
    private JwtProvider jwtProvider;

	@Override
	public User signInUserAndReturnJWT(User signInRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrinciple);

        User signInUser = userPrinciple.getUser();
        signInUser.setJwtToken(jwt);

        return signInUser;
	}

}
