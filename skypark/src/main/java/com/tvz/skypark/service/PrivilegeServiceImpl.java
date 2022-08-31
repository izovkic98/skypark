package com.tvz.skypark.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvz.skypark.model.Reservation;
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

		List<Reservation> list = reservationRepository.findByUser_IdLike(user.getId());

		if (list.size() > 1 && list.size() <= 3) {
			user.setTier(Tier.SILVER);
		} else if (list.size() > 3 && list.size() <= 4) {
			user.setTier(Tier.GOLD);
		} else if (list.size() > 4) {
			user.setTier(Tier.PLATINUM);
		}

		return user.getTier();
	}
	
}
