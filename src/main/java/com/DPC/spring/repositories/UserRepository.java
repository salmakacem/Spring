package com.DPC.spring.repositories;

import com.DPC.spring.DTO.UserDto;
import com.DPC.spring.entities.Adress;
import com.DPC.spring.entities.User;
import com.DPC.spring.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // return True if email already exist
    boolean existsByEmail(String email);
    // find user by email address
    User findByEmail(String email);

    Optional<User> findOneByEmailIgnoreCase(String email);
    Optional<User> findOneByResetKey(String key);
    Optional<User> findById(long id);
    Optional<User> findByResetPasswordToken(String token);
    Optional<User> findByDetails(UserDetails userDetails);


}
