package com.DPC.spring.repositories;


import com.DPC.spring.entities.ImageModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel, String> {

    // ImageModel findByUser(User u);
    //Optional<ImageModel> findByName(String imageName);
}
