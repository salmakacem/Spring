package com.DPC.spring.services.Impl;

import com.DPC.spring.entities.PushNotifRequest;
import com.DPC.spring.services.FCMService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;




@Service

public class PushNotificationService {
    private Logger logger = LoggerFactory.getLogger(com.DPC.spring.services.Impl.PushNotificationService.class);

    private FCMService fcmService;

    public PushNotificationService(FCMService fcmService) {
        this.fcmService = fcmService;
    }


    public void sendPushNotificationToToken(Map<String, String> data, PushNotifRequest request) {
        try {
            fcmService.sendMessageToTokenWithData(data,request);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    public void sendPushNotificationWithoutData(PushNotifRequest request) {
        try {
            fcmService.sendMessageWithoutData(request);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    public void sendPushNotificationCustomDataWithTopic(Map<String, String> data, PushNotifRequest request) {
        try {
            fcmService.sendMessageCustomDataWithTopic(data, request);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }





}
