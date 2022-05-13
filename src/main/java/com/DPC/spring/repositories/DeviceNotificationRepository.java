package com.DPC.spring.repositories;

import com.DPC.spring.entities.DeviceNotif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceNotificationRepository extends JpaRepository<DeviceNotif,Long> {
    Optional<DeviceNotif> findByUserId(String string);
}
