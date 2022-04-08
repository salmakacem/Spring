package com.DPC.spring.services.Impl;

import com.DPC.spring.entities.User;
import com.DPC.spring.repositories.UserRepository;
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
    @Autowired
    UserRepository userRepository;
    public void EnvoyerEmail(User user) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject("Réinitialiser le mot de passe Forum ");
            mimeMessageHelper.setFrom("contact@dsms.world");
            mimeMessageHelper.setTo("selma.kacem@istic.ucar.tn");
            String url = "http://localhost:4200/motdepasse";
            String content = "Bonjour, " + user.getEmail()
                    //+ "<br>Votre mot de passe est : \n"  +user.getResetKey()+ "\n"
                    + "<br>Vous pouvez accéder au espace changer mdp via l'adresse suivante : \n" + "<a href=\"http://localhost:4200/reset/finish?key=" + user.getResetKey() + "\n"
                    + " <br><br> Cordialement,";
            mimeMessageHelper.setText(content);
            mimeMessageHelper.setText("<html><body><p>" + content
                            + "</p> </body></html>",
                    true);
            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
        public void verificationcode(User user) {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            try {

                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

                mimeMessageHelper.setSubject("Vérification de l'existance du compte email");
                mimeMessageHelper.setFrom("contact@dsms.world");
                mimeMessageHelper.setTo(user.getEmail());
                String url = "http://localhost:4200/motdepasse";
                String content = "Bonjour, "+user.getEmail()
                        + "<br>Votre code est : \n"  +user.getResetKey()+ "\n"
                       // + "<br>Vous pouvez accéder au espace changer mdp via l'adresse suivante : \n" + "<a href=\"http://localhost:4200/reset/finish?key=" +user.getResetKey()+ "\n"
                        + " <br><br> Cordialement,";
                mimeMessageHelper.setText(content);
                mimeMessageHelper.setText("<html><body><p>" + content
                                + "</p> </body></html>",
                        true);
                mailSender.send(mimeMessageHelper.getMimeMessage());
            } catch (MessagingException e) {
                e.printStackTrace();
            }

    }



    }

