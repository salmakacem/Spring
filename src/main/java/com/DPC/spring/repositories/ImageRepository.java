package com.DPC.spring.repositories;

import com.DPC.spring.entities.Archive;
import com.DPC.spring.entities.ImageModel;
import com.DPC.spring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    ImageModel findByUser(User u);
    //Optional<ImageModel> findByName(String imageName);
}
