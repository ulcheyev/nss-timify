package com.kyki.notificationmicrosevice.notificationmethod;

import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailSend
{
    private static String host = "smtp.gmail.com";
    private static String from  = "timify.kykiko@gmail.com";

    private static Properties properties;
    private static Authenticator authenticator;



    public static void config()
    {
        if (properties==null)
        {
            properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");


            authenticator = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("timyfy.kykiko@gmail.com", "pvamlnfzykzgqkbq");
                }
            };
        }
    }

    public static void send(String text, String to)
    {
        Session session = Session.getInstance(properties, authenticator);
        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setText(text);
            message.setSubject("Notification!!!!!!!!!!!!!!!!!!!!!");
            Transport.send(message);
            System.out.println("sent");
        }
        catch (AddressException e)
        {
            System.out.println("Address is not valid " + e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
