/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LandingPage;

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
    private Properties transportProperties;
    private String username;
    private String password;
    private String emailSubject;
    private String emailText;
    private String emailFromAddress;
    private String emailFromPersonal;
    private String emailToAddress;
    private String attachFilePath;
    private String attachFilePsevdonim;

    private void setupTransportProperties(TransportLayer transport) {
        this.transportProperties = new Properties();

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

    private void putTLSProperties() {
        this.transportProperties.put("mail.smtp.auth", "true");
        this.transportProperties.put("mail.smtp.starttls.enable", "true");
        this.transportProperties.put("mail.smtp.host", "smtp.gmail.com");
        this.transportProperties.put("mail.smtp.port", "587");
    }

    private void putSSLProperties() {
        this.transportProperties.put("mail.smtp.host", "smtp.gmail.com");
        this.transportProperties.put("mail.smtp.socketFactory.port", "465");
        this.transportProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        this.transportProperties.put("mail.smtp.auth", "true");
        this.transportProperties.put("mail.smtp.port", "465");
    }

    public Sender(TransportLayer transport, String username, String password) {
        this.username = username;
        this.password = password;
        setupTransportProperties(transport);
    }

    public Sender(TransportLayer transport) {
        setupTransportProperties(transport);
    }

    public Sender() {
        setupTransportProperties(TransportLayer.TLS);
    }

    public void config(
            TransportLayer transport,
            String username, String password,
            String emailSubject,
            String emailText,
            String emailFromAddress, String emailFromPersonal,
            String emailToAddress,
            String attachFilePath, String attachFilePsevdonim) {

        setupTransportProperties(transport);
        
        configAuthentication(username, password);

        configEmail(emailSubject, emailText,
                emailFromAddress, emailFromPersonal,
                emailToAddress,
                attachFilePath, attachFilePsevdonim);
    }

    public void configAuthentication(
            String username, String password) {

        this.username = username;
        this.password = password;
    }

    public void configEmail(
            String emailSubject,
            String emailText,
            String emailFromAddress, String emailFromPersonal,
            String emailToAddress,
            String attachFilePath, String attachFilePsevdonim) {

        this.emailSubject = emailSubject;
        this.emailText = emailText;
        this.emailFromAddress = emailFromAddress;
        this.emailFromPersonal = emailFromPersonal;
        this.emailToAddress = emailToAddress;
        this.attachFilePath = attachFilePath;
        this.attachFilePsevdonim = attachFilePsevdonim;
    }

    public void send() {
        Session session = Session.getInstance(transportProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(emailFromAddress, emailFromPersonal));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailToAddress));

            message.setSubject(emailSubject, "UTF-8");

            createMultipartEmail(message, emailText, attachFilePath, attachFilePsevdonim);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(
            String emailSubject,
            String emailText,
            String emailFromAddress, String emailFromPersonal,
            String emailToAddress,
            String attachFilePath, String attachFilePsevdonim) {

        configEmail(emailSubject, emailText,
                emailFromAddress, emailFromPersonal,
                emailToAddress,
                attachFilePath, attachFilePsevdonim);
        
        send();
    }

    private void createMultipartEmail(
            MimeMessage message,
            String text,
            String attachFileName, String attachFilePsevdonim) throws MessagingException, UnsupportedEncodingException {
        // Create a multipar message
        Multipart multipart = new MimeMultipart();

        // Create the message part 
        BodyPart messageBodyPart = new MimeBodyPart();
        // Fill the message
        //messageBodyPart.setText(text);
        //byte[] htmlData = text.getBytes("utf-8");
        //mailMessage.setText(MimeUtility.encodeText(mail.getMessage(), "UTF-8", "Q"));
        messageBodyPart.setHeader("Content-Type", "text/html; charset=UTF-8");
        messageBodyPart.setHeader("Content-Encoding", "UTF-8");
        messageBodyPart.setContent(text, "text/html; charset=UTF-8");

        // Set text message part
        multipart.addBodyPart(messageBodyPart);

        // Part two is attachment
        MimeBodyPart attachment = new MimeBodyPart();

        DataSource source = new FileDataSource(attachFileName);
        attachment.setDataHandler(new DataHandler(source));
        attachment.setFileName(attachFilePsevdonim);
        //attachment.attachFile(attachFileName);

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

//        
//        MimeBodyPart attachmentPart = new MimeBodyPart();
//        try {
//            DataSource ds = new ByteArrayDataSource(attachment.getBytes("UTF-8"), "application/octet-stream");
//            attachmentPart = new MimeBodyPart();
//            attachmentPart.setDataHandler(new DataHandler(ds));
//        } catch (Exception e) {
//            Logger.getLogger("Blina").log(Level.SEVERE, Misc.getStackTrace(e));
//        }
//
//        attachmentPart.setFileName(fileName);
//        multipart.addBodyPart(attachmentPart);
//
//        // Put parts in message
//        msg.setContent(multipart);

    }
}
