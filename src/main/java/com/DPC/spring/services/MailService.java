package com.DPC.spring.services;

import com.DPC.spring.entities.User;

public interface MailService {
    void EnvoyerEmail(User user);
}
