package com.DPC.spring.repositories;

import com.DPC.spring.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    @Query( "select D from UserDetails D inner join D.user u on u.id =:id" )
    Optional<UserDetails> findByUser(long id);

}
