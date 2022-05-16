package com.DPC.spring.services;

import java.util.List;

public interface DeviceNotificationService {
    void saveOrUpdateDevice(String typeDevice, String tokenNotification, Long idUser);

    List<String> getTokensPushNotification(Long idUser);


}
