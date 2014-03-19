/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LandingPage;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author 1
 */
@Stateless
@LocalBean
public class EmailSessionBean {

    private int port = 465;
    private String host = "smtp.example.com";
    private String from = "matt@example.com";
    private boolean auth = true;
    private String username = "matt@example.com";
    private String password = "secretpw";
//private Protocol protocol = Protocol.SMTPS;
    private boolean debug = true;
    private static Sender tlsSender = new Sender(Sender.TransportLayer.TLS, "rul@moneypoolator.com", "e5U86J45PR_");
    private static Sender sslSender = new Sender(Sender.TransportLayer.SSL, "keeper@moneypoolator.com", "intheairtonight");

    //@Asynchronous
    protected void sendEmail(String goback, String email) {
        String emailSubject = "This is Subject";
        String emailText = "This is text!";
        String emailFromAddress = "support@moneypoolator.com";
        String emailFromPersonal = "Moneypoolator support team";
        String attachFileName = /*getServletContext().getRealPath*/("/pdf/moneypoolator31.pdf");

        if (goback != null) {

            if (goback.contains("landing31") || goback.contains("index")) {
                attachFileName = /*getServletContext().getRealPath*/("/pdf/moneypoolator31.pdf");
            } else if (goback.contains("landing32")) {
                attachFileName = /*getServletContext().getRealPath*/("/pdf/moneypoolator32.pdf");
            }
        }

        //tlsSender.send(emailSubject, emailText, emailFromAddress, emailFromPersonal, email, attachFileName);
        //sslSender.send(emailSubject, emailText, emailFromAddress, emailFromPersonal, email, attachFileName);
    }
}
