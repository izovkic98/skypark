package com.tvz.skypark.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvz.skypark.model.User;
import com.tvz.skypark.repository.ReservationRepository;
import com.tvz.skypark.repository.UserRepository;
import com.tvz.skypark.utils.ParkUtils.Tier;
import com.tvz.skypark.utils.PointsCalculator;

@Service
public class PrivilegeServiceImpl implements PrivilegeService{
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PointsCalculator pointsCalculator;

	@Override
	public Integer loyaltyPoints(User user, float amountSpent) {

		Tier tier = user.getTier();
		Integer newPoints = pointsCalculator.calculateTotalPoints(tier, amountSpent);
		
		//setiranje novih bodova + sejvanje usera sa novim bodovima
		if(user.getLoyaltyPoints() != null) {
			user.setLoyaltyPoints(user.getLoyaltyPoints() + newPoints);
		} else {
			user.setLoyaltyPoints(newPoints);
		}

		user.setTier(updateTier(user));
		userRepository.save(user);
		
		return user.getLoyaltyPoints();
	}

	@Override
	public Tier updateTier(User user) {

		if (user.getLoyaltyPoints() > 25 && user.getLoyaltyPoints() <= 35) {
			user.setTier(Tier.SILVER);
		} else if (user.getLoyaltyPoints() > 35 && user.getLoyaltyPoints() <= 55) {
			user.setTier(Tier.GOLD);
		} else if (user.getLoyaltyPoints() > 55) {
			user.setTier(Tier.PLATINUM);
		}

		return user.getTier();
	}
	
}
