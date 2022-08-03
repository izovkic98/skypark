package com.tvz.skypark.repository.projection;

import java.time.LocalDate;

public interface ReservationItem {
	
	String getName();
	String getPrice();
	LocalDate getReservationTime();

}
 