package com.tvz.skypark.service;

import com.tvz.skypark.model.User;

public interface AuthenticationService {

	User signInUserAndReturnJWT(User signInRequest);
	
}
