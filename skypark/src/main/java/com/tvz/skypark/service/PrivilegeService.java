package com.tvz.skypark.service;

import org.springframework.stereotype.Service;

import com.tvz.skypark.model.User;
import com.tvz.skypark.utils.ParkUtils.Tier;


@Service
public interface PrivilegeService {

	Integer loyaltyPoints(User user, float amountSpent);
	Tier updateTier(User user);

}
