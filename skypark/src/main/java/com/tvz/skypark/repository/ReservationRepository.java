package com.tvz.skypark.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tvz.skypark.model.Reservation;
import com.tvz.skypark.model.User;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
//    @Query("select " +
//            "veh.name as name, res.price as price, res.reservationDate as reservationDate " +
//            "from Reservation res left join Vehicle veh on veh.id = res.vehicleId " +
//            "where res.userId = :userId")
//    List<ReservationItem> findAllReservationOfUser(@Param("userId") Long userId);
	
    Reservation findByReservationDateLike(LocalDate reservationDate);
	Reservation findByUserLike(User user);
	List<Reservation> findByUser_UsernameLike(String username);
	

}
