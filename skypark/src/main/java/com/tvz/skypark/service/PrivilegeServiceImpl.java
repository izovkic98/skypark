package com.tvz.skypark.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvz.skypark.model.Discount;
import com.tvz.skypark.model.User;
import com.tvz.skypark.repository.DiscountRepository;
import com.tvz.skypark.repository.ReservationRepository;
import com.tvz.skypark.utils.ParkUtils.Tier;
import com.tvz.skypark.utils.PointsCalculator;

@Service
public class PrivilegeServiceImpl implements PrivilegeService{
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	private DiscountRepository discountRepository;
	
	@Autowired
	private PointsCalculator pointsCalculator;

	@Override
	public Integer loyaltyPoints(User user, float amountSpent) {
		
		Discount discount = discountRepository.findByUser_IdLike(user.getId());

		Tier tier = discount.getTier();
		Integer newPoints = pointsCalculator.calculateTotalPoints(tier, amountSpent);
		
		//setiranje novih bodova + sejvanje usera sa novim bodovima
		if(discount.getLoyaltyPoints() != null) {
			discount.setLoyaltyPoints(discount.getLoyaltyPoints() + newPoints);
		} else {
			discount.setLoyaltyPoints(newPoints);
		}

		discount.setTier(updateTier(discount));
		discountRepository.save(discount);
		
		return discount.getLoyaltyPoints();
	}

	@Override
	public Tier updateTier(Discount discount) {

		if (discount.getLoyaltyPoints() > 5 && discount.getLoyaltyPoints() <= 10) {
			discount.setTier(Tier.SILVER);
		} else if (discount.getLoyaltyPoints() > 10 && discount.getLoyaltyPoints() <= 20) {
			discount.setTier(Tier.GOLD);
		} else if (discount.getLoyaltyPoints() > 20) {
			discount.setTier(Tier.PLATINUM);
		}

		return discount.getTier();
	}
	
}
