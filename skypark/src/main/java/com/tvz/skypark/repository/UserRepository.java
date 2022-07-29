package com.tvz.skypark.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tvz.skypark.model.User;
import com.tvz.skypark.utils.ParkUtils.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername (String username);
	
	@Modifying
	@Query("update User set role = :role where username = :username")
	void updateUserRole(@Param("username") String username, @Param("role") Role role);
	
	
	User findByUsernameLike(String username);
	User findByEmailLike(String email);
	User findByUsernameLikeAndPasswordLike(String username, String password);	
 
}
