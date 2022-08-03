package com.tvz.skypark.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tvz.skypark.model.Reservation;
import com.tvz.skypark.model.User;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
    @Query(value = "SELECT * FROM reservation res where res.user_id = :userId",
            nativeQuery = true)
    List<Reservation> findAllReservationOfUser(@Param("userId") Long userId);
    
	List<Reservation> findByUser_IdLike(@Param("userId") Long userId);
	
    Reservation findByReservationDateLike(LocalDate reservationDate);
	Reservation findByUserLike(User user);
	List<Reservation> findByUser_UsernameLike(String username);
	

}
