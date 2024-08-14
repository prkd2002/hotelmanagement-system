package com.prkd.HotelServer.repository;

import com.prkd.HotelServer.entity.User;
import com.prkd.HotelServer.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{
    Optional<User> findUserByEmail(String email);
    Optional<User> findByUserRole(UserRole userRole);
}
