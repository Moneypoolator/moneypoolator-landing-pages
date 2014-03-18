/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nan.sendmail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author 1
 */
public class Sender {

    public enum TransportLayer {
        TLS,
        SSL
    };
    private String username;
    private String password;
    private Properties props;

    private void putTLSProperties() {
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    private void putSSLProperties() {
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
    }

    public Sender(TransportLayer transport, String username, String password) {
        this.username = username;
        this.password = password;

        props = new Properties();

        switch (transport) {
            case TLS:
                putTLSProperties();
                break;
            case SSL:
                putSSLProperties();
                break;
            default:
                break;
        }
    }

    public void send(String subject, String text, String fromEmail, String toEmail) {
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);

            //от кого
            message.setFrom(new InternetAddress(fromEmail, "Moneypoolator support"));
            //кому
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            //Заголовок письма
            message.setSubject(subject);
            
            //Содержимое
//            message.setText(text);
            createFileEmail(message, "invoice.pdf");

            //Отправляем сообщение
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void createFileEmail(MimeMessage message, String filename) throws MessagingException {
        // Create a multipar message
        Multipart multipart = new MimeMultipart();

        // Create the message part 
        BodyPart messageBodyPart = new MimeBodyPart();
        // Fill the message
        messageBodyPart.setText("This is message body");

        // Set text message part
        multipart.addBodyPart(messageBodyPart);

        // Part two is attachment
        MimeBodyPart attachment = new MimeBodyPart();

        DataSource source = new FileDataSource(filename);
        attachment.setDataHandler(new DataHandler(source));
        attachment.setFileName(filename);
        
        multipart.addBodyPart(attachment);

        // Send the complete message parts
        message.setContent(multipart);

//        String htmlBody;        // ...
//        byte[] attachmentData;  // ...
//
//        Multipart mp = new MimeMultipart();
//
//        MimeBodyPart htmlPart = new MimeBodyPart();
//        htmlPart.setContent(htmlBody, "text/html");
//        mp.addBodyPart(htmlPart);
//
//        MimeBodyPart attachment = new MimeBodyPart();
//        attachment.setFileName("manual.pdf");
//        attachment.setContent(attachmentData, "application/pdf");
//        mp.addBodyPart(attachment);
//
//        message.setContent(mp);
    }
}
