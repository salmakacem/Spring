package com.DPC.spring.services.Impl;

import com.DPC.spring.entities.User;
import com.DPC.spring.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    JavaMailSender mailSender;
    public void EnvoyerEmail(User user) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject("Compte parent ");
            mimeMessageHelper.setFrom("contact@dsms.world");
            mimeMessageHelper.setTo(user.getEmail());
            String url = "http://localhost:4200/motdepasse";
            String content = "Bonjour ( Mme), "+user.getEmail()
                    + "<br>Votre mot de passe est : \n"  + "\n"
                    + "<br>Vous pouvez accéder au espace parent via l'adresse suivante : \n" + url + "\n"
                    + " <br><br> Cordialement,";

            mimeMessageHelper.setText(content);
            mimeMessageHelper.setText("<html><body><p>" + content
                            + "</p> <img src=\"http://207.180.211.158/photo/assets/images/logo_dsms.png\" width=\"50\" alt=\"Apen\"></body></html>",
                    true);
            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
