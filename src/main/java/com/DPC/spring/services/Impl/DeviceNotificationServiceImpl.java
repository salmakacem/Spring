package com.DPC.spring.services.Impl;


import com.DPC.spring.Mappers.MappersDto;
import com.DPC.spring.entities.DeviceNotif;
import com.DPC.spring.repositories.*;

import com.DPC.spring.services.DeviceNotificationService;
import com.DPC.spring.services.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;


@Service
@Slf4j
public class DeviceNotificationServiceImpl implements DeviceNotificationService {

    @Autowired
   DeviceNotificationRepository deviceNotificationRepository ;

    @Autowired
    UserRepository userRepository;






    @Autowired
    PushNotificationService pushNotificationService;

    final MappersDto mappersDto;

    @Autowired
    MailService mailService;


    public DeviceNotificationServiceImpl(MappersDto mappersDto) {
        this.mappersDto = mappersDto;
    }

    @Override
    public void saveOrUpdateDevice(String typeDevice, String tokenNotification, Long idUser) {


        Optional<DeviceNotif> deviceExist = this.deviceNotificationRepository.findByUserId(idUser.toString());

        if (deviceExist.isPresent()) {
            DeviceNotif device = deviceExist.get();
            if (typeDevice.equals("webToken")) {
                device.setWebToken(tokenNotification);
                this.deviceNotificationRepository.saveAndFlush(device);
            } else if (typeDevice.equals("androidToken")) {
                device.setAndroidToken(tokenNotification);
                this.deviceNotificationRepository.saveAndFlush(device);
            } else if (typeDevice.equals("iosToken")) {
                device.setIosToken(tokenNotification);
                this.deviceNotificationRepository.saveAndFlush(device);
            }
        } else {
            DeviceNotif device = new DeviceNotif();
            if (typeDevice.equals("webToken")) {
//                System.out.println(typeDevice + "typeDevice");

                device.setWebToken(tokenNotification);
                device.setAndroidToken("");
                device.setIosToken("");
                device.setUserId(idUser.toString());
                System.out.println(device + "");
                this.deviceNotificationRepository.save(device);
            } else if (typeDevice.equals("androidToken")) {
                device.setWebToken("");
                device.setAndroidToken(tokenNotification);
                device.setIosToken("");
                device.setUserId(idUser.toString());
                this.deviceNotificationRepository.save(device);
            } else if (typeDevice.equals("iosToken")) {
                device.setWebToken("");
                device.setAndroidToken("");
                device.setIosToken(tokenNotification);
                device.setUserId(idUser.toString());
                this.deviceNotificationRepository.save(device);
            }

        }

    }

    @Override
    public List<String> getTokensPushNotification(Long idUser) {
        List<String> tokens = new ArrayList<>();
        Optional<DeviceNotif> deviceExist = this.deviceNotificationRepository.findByUserId(idUser.toString());
        if (deviceExist.isPresent()) {
            DeviceNotif device = deviceExist.get();
            if (!device.getAndroidToken().isEmpty()) {
                tokens.add(device.getAndroidToken());
            }
            if (!device.getIosToken().isEmpty()) {
                tokens.add(device.getIosToken());
            }
            if (!device.getWebToken().isEmpty()) {
                tokens.add(device.getWebToken());
            }
            return tokens;

        } else {
            return tokens;
        }


    }

}
