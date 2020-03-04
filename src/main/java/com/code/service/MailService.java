package com.code.service;

import com.code.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Properties;

@Service
public class MailService {

    @Autowired
    UserService userService;

    public Object sendMail(String mailSubject, String mailBody, String sendTo){
        User system = userService.getUserById(1L);
        String eMail = system.getEmail();
        //String pMail = system.getEmailpassword();
        String pwd = system.getEmailpassword();
        Base64.Decoder decoder = Base64.getDecoder();
        // Decoding string
        String pMail = new String(decoder.decode(pwd));

        // Set required configs
        String from = "system.tondira@gmail.com";
        //String to = "sabar@tondira.com";
        String host = "smtp.gmail.com";
        String port = "587";
        //String user = "system.tondira@gmail.com";
        //String password = "tondira123";

        // Set system properties
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port);
        properties.setProperty("mail.smtp.user", eMail);
        properties.setProperty("mail.smtp.password", pMail);
        properties.setProperty("mail.smtp.starttls.enable", "true");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set from email address
            message.setFrom(new InternetAddress(from, "Bootpiere Notification"));
            // Set the recipient email address
            message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(sendTo));
            // Set email subject
            message.setSubject(mailSubject);
            // Set email body
            message.setText(mailBody);
            // Set configs for sending email
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pMail);
            // Send email
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("done");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
