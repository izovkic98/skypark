//package com.tvz.skypark.repository;
//
//
//import java.time.LocalDate;
//import java.util.List;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//import com.tvz.skypark.model.Reservation;
//import com.tvz.skypark.model.User;
//import com.tvz.skypark.repository.projection.ReservationItem;
//
//@Repository
//public interface ReservationRepository extends JpaRepository<Reservation, Long> {
//	
//    @Query("select " +
//            "veh.name as name, res.price as price, res.reservationDate as reservationDate " +
//            "from Reservation res left join Vehicle veh on veh.id = res.vehicleId " +
//            "where res.userId = :userId")
//    List<ReservationItem> findAllReservationOfUser(@Param("userId") Long userId);
//    
//    
//   
//
//}
