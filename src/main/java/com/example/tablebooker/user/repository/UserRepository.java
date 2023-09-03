package com.example.tablebooker.user.repository;

import com.example.tablebooker.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<Object> findByEmail(String email);

    Optional<Object> findByMobileNumber(String mobileNumber);


}
