package com.DPC.spring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PushNotifRequest {
    private String title;
    private String message;
    private String topic;
    private String token;
	Map<String, String> data;
    

    
}