package LandingPage;

import java.text.DateFormat;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.mail.internet.AddressException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author 1
 */
public class SubscriberServlet extends HttpServlet {

    @Resource(name = "jdbc/LPDB")
    private DataSource jdbcLPDB;
//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("landing-pagePU");
    public static final String EMAIL_REGEX =
            "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
//    private static Sender tlsSender = new Sender(Sender.TransportLayer.TLS, "rul@moneypoolator.com", "e5U86J45PR_");
//    private static Sender sslSender = new Sender(Sender.TransportLayer.SSL, "keeper@moneypoolator.com", "intheairtonight");

    /**
     * Validates email address agains email regular expression.
     *
     * @param email an email address to check
     * @return true if email address is valid otherwise return false.
     */
    protected boolean isValidEmailAddress(String email) {
        return email.matches(EMAIL_REGEX);
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String contextPath = request.getContextPath();
        HttpSession session = request.getSession();

        String announceEmail = request.getParameter("announce-email");
        String documentEmail = request.getParameter("document-email");
        // for old pages 
        String oldEmail = request.getParameter("email");

        String email = null;
        boolean there_is_document_request = true;

        if (announceEmail != null) {
            email = announceEmail;
            there_is_document_request = false;
        } else if (documentEmail != null) {
            email = documentEmail;
            there_is_document_request = true;
        } else if (oldEmail != null) {
            email = oldEmail;
            there_is_document_request = true;
        }

        boolean email_is_valid = false;
        boolean email_saving_complete = false;
        String email_error_message = "";
        if (email != null) {
            // Email is specified as request parameter, do the business logic here.
            if (email.trim().isEmpty()) {
                email_is_valid = false;
                email_error_message = "Это обязательное поле";
            } else if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                email_is_valid = false;
//                session.setAttribute("currentAdress", email);
                email_error_message = "Введите адрес электронной почты";
            } else {
                try {
                    javax.mail.internet.InternetAddress ia = new javax.mail.internet.InternetAddress(email);
                    ia.validate();
                    email_is_valid = true;
                } catch (javax.mail.internet.AddressException ae) {
                    email_is_valid = false;
//                    session.setAttribute("currentAdress", email);
                    email_error_message = "Проверьте адрес электронной почты";
                }
            }
        }

        if (email_is_valid) {

//            Subscribers man = getServiceLocator();
//            man.setEmail(email);
//
//            persist(man);

//            Context env = null;
//            DataSource ds = null;
            
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs;

            try {

                String insertSubscriberSQL = "INSERT INTO subscribers (email, created, ip_adress, ab_page) VALUES ('"
                        + email + "', NOW(), '"
                        + request.getRemoteAddr() + "', 1);";
                String selectEmailSQL = "SELECT id FROM subscribers WHERE email = '" + email + "';";

//                env = new InitialContext();
//                ds = (DataSource) env.lookup("jdbc/LPDB");

                conn = jdbcLPDB.getConnection();
                stmt = conn.createStatement();

                rs = stmt.executeQuery(selectEmailSQL);
                if (rs.first()) {
//                    session.setAttribute("currentAdress", email);
                    email_saving_complete = false;
                    email_error_message = "Подписка на указанный адрес уже оформлена.";
                } else {

                    int res = stmt.executeUpdate(insertSubscriberSQL);
                    if (res > 0) {
//                        String emailSubject = "Документ про рынок прогнозов";
//                        String emailText;
////                        StringBuilder sb = new StringBuilder();
////
////                        sb.append("Здравствуйте!\n");
////                        sb.append("Это письмо от сервиса Moneypoolator\n");
////                        sb.append("Мы прислали вам документ, в котором объяснены принципы работы рынка прогнозов.\n");
////                        sb.append("Еще раз спасибо, что дали возможность рассказать о нас.\n");
////                        sb.append("Связаться с нами: support@moneypoolator.com\n");
//
//                        emailText = getEmailText("http://moneypoolator.ru");
//
//
//                        String emailFromAddress = "support@moneypoolator.com";
//                        String emailFromPersonal = "Moneypoolator support team";
//                        String attachFileName = getServletContext().getRealPath("/pdf/moneypoolator31.pdf");
//                        String attachFilePsevdonim = "moneypoolator31.pdf";
//
//                        String goback = request.getParameter("goback");
//                        if (goback != null) {
//                            if (goback.contains("landing31") || goback.contains("index")) {
//                                attachFileName = getServletContext().getRealPath("/pdf/moneypoolator31.pdf");
//                                attachFilePsevdonim = "moneypoolator31.pdf";
//                            } else if (goback.contains("landing32")) {
//                                attachFileName = getServletContext().getRealPath("/pdf/moneypoolator32.pdf");
//                                attachFilePsevdonim = "moneypoolator32.pdf";
//                            }
//                        }
//
//                        tlsSender.send(emailSubject, emailText, emailFromAddress, emailFromPersonal, email, attachFileName, attachFilePsevdonim);
//                        //sslSender.send(emailSubject, emailText, emailFromAddress, emailFromPersonal, email, attachFileName);

//                        request.setAttribute("subscriberEmail", email);
                        email_saving_complete = true;
                        email_error_message = "Поздравляем, ваш адрес зарегистрирован! Приглашение будет отправлено на указанный адрес.";
                    } else {

//                        session.setAttribute("currentAdress", email);
                        email_saving_complete = false;
                        email_error_message = "Не удалось сохранить указанный адрес. Повторите попытку позже.";
                    }
                }

            } catch (Exception e) {

                //out.println("Login Failed! Database error! Invalid user login or password!<br/>");
//                throw new ServletException(e.getMessage());
                email_saving_complete = false;
                email_error_message = "Не удалось сохранить указанный адрес. Повторите попытку позже.";

            } finally {
                try {
//                    if (env != null) {
//                        env.close();
//                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException sqle) {
//                } catch (NamingException ex) {
                    //Logger.getLogger(SubscriberServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } // try            

//            HttpSession session = request.getSession();
//            session.setAttribute("mailErrorResponse", email_error_message);
//            response.sendRedirect(response.encodeRedirectURL(contextPath + "/index"));
//
        } else {
//            request.setAttribute("subscribersCount", 435);
//            request.setAttribute("subscribersSuffix", "человека");
//            request.setAttribute("mailErrorResponse", "Это обязательное поле");
//
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
//            dispatcher.forward(request, response);
        } // if

        String anchor = "#document-request";
        if (there_is_document_request) {
            anchor = "#document-request";
            session.setAttribute("documentAddress", email);
            session.setAttribute("documentAddressError", email_error_message);
            // for old pages
            session.setAttribute("currentAdress", email);
            session.setAttribute("mailErrorResponse", email_error_message);
        } else {
            anchor = "#announce-request";
            session.setAttribute("announceAddress", email);
            session.setAttribute("announceAddressError", email_error_message);
        }

        if (!email_is_valid || !email_saving_complete) {

            String goback = request.getParameter("goback");
            if (goback == null) {
                goback = "/index";
            }
            goback += anchor;

//            response.setHeader("Pragma", "no-cache"); //HTTP/1.0 Proxy Servers
//            response.setHeader("Cache-Control", "no-cache"); //HTTP/1.1 Proxy Servers
//            response.setDateHeader("Expires", 0); // for Browsers  
            response.sendRedirect(response.encodeRedirectURL(contextPath + goback));

//            RequestDispatcher dispatcher = request.getRequestDispatcher(contextPath + goback);
//            dispatcher.forward(request, response);

        } else {

            String forward = "/AsyncEmailSenderServlet";
            RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
            dispatcher.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

//    private String host;
//    private String port;
//    private String user;
//    private String pass;
    @Override
    public void init() throws ServletException {
        super.init();

        // reads SMTP server setting from web.xml file
//        ServletContext context = getServletContext();
//        host = context.getInitParameter("host");
//        port = context.getInitParameter("port");
//        user = context.getInitParameter("user");
//        pass = context.getInitParameter("pass");

        try {
            Context env;
            env = (Context) new InitialContext().lookup("java:comp/env");
            jdbcLPDB = (DataSource) env.lookup("jdbc/LPDB");

            if (jdbcLPDB == null) {
                throw new ServletException("'jdbc:mysql://localhost:3306/landing_page' is in unknown DataSource");
            }
        } catch (NamingException ne) {
            throw new ServletException(ne.getMessage());
        }

    }
//    private DataSource getJdbcLPDB() {
//        return jdbcLPDB;
//    }
//    public void persist(Object object) {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.persist(object);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
//            em.getTransaction().rollback();
//        } finally {
//            em.close();
//        }
//    }
}
