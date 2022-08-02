package com.tvz.skypark.service;

import com.tvz.skypark.dto.UserLoginDto;
import com.tvz.skypark.model.User;

public interface AuthenticationService {

	User signInUserAndReturnJWT(UserLoginDto signInRequest);
	
}
