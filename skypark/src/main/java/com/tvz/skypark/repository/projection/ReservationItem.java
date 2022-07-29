package com.tvz.skypark.repository.projection;

import java.time.LocalDateTime;

public interface ReservationItem {
	
	String getName();
	String getPrice();
	LocalDateTime getReservationTime();

}
 