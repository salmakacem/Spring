package com.DPC.spring.repositories;

import com.DPC.spring.entities.Adress;
import com.DPC.spring.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Long> {


    @Query( "select D from Adress D inner join D.userDetails u on u.id =:id" )
    Optional<Adress> findByUserDetails(long id);

}
