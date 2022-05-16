package com.DPC.spring.controllers;


import com.DPC.spring.entities.PushNotifRequest;
import com.DPC.spring.entities.PushNotifResponse;
import com.DPC.spring.services.DeviceNotificationService;
import com.DPC.spring.services.Impl.PushNotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class PushNotificationController {

    private static final String DATE_PATTERN = "yyyy-MM-dd";


    private PushNotificationService pushNotificationService;
    final DeviceNotificationService deviceNotificationService;

    public PushNotificationController(PushNotificationService pushNotificationService, DeviceNotificationService deviceNotificationService) {
        this.pushNotificationService = pushNotificationService;
        this.deviceNotificationService = deviceNotificationService;
    }

    @PostMapping("/notification/token")
    public ResponseEntity sendTokenNotification(@RequestBody PushNotifRequest request) {
        pushNotificationService.sendPushNotificationWithoutData(request);
        System.out.println("princr");
        return new ResponseEntity<>(new PushNotifResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }



    @PostMapping("/notification/topic")
    public ResponseEntity sendNotification(@RequestBody PushNotifRequest request) {
        pushNotificationService.sendPushNotificationCustomDataWithTopic(request.getData(),request);
        return new ResponseEntity<>(new PushNotifResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

//    @GetMapping("/sendNotificationForUserNotPointedToday")
//    public List<String> sendNotificationForUserNotPointedToday() throws FirebaseMessagingException {
//        return this.deviceNotificationService.saveOrUpdateDevice( );
//
//    }
}
