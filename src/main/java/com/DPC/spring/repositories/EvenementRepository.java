package com.DPC.spring.repositories;

import com.DPC.spring.entities.Evenement;
import com.DPC.spring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement , Long> {
    Evenement findById(String id);
}
