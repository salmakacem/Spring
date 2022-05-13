package com.DPC.spring.services;

import com.DPC.spring.entities.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface MailService {
    void EnvoyerEmail(User user);
    void verificationcode(User user);

}
