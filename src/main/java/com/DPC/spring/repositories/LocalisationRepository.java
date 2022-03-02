package com.DPC.spring.repositories;

import com.DPC.spring.entities.Localisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocalisationRepository extends JpaRepository<Localisation, Long> {
}
