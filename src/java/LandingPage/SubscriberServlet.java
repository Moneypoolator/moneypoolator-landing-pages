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
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("landing-pagePU");
    public static final String EMAIL_REGEX =
            "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    private static Sender tlsSender = new Sender(Sender.TransportLayer.TLS, "rul@moneypoolator.com", "e5U86J45PR_");
    private static Sender sslSender = new Sender(Sender.TransportLayer.SSL, "keeper@moneypoolator.com", "intheairtonight");

    protected void sendEmail(String goback, String email) {
        String emailSubject = "This is Subject";
        String emailText = "This is text!";
        String emailFromAddress = "support@moneypoolator.com";
        String emailFromPersonal = "Moneypoolator support team";
        String attachFileName = getServletContext().getRealPath("/pdf/moneypoolator31.pdf");

        if (goback != null) {

            if (goback.contains("landing31") || goback.contains("index")) {
                attachFileName = getServletContext().getRealPath("/pdf/moneypoolator31.pdf");
            } else if (goback.contains("landing32")) {
                attachFileName = getServletContext().getRealPath("/pdf/moneypoolator32.pdf");
            }
        }

        //tlsSender.send(emailSubject, emailText, emailFromAddress, emailFromPersonal, email, attachFileName);
        //sslSender.send(emailSubject, emailText, emailFromAddress, emailFromPersonal, email, attachFileName);
    }

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

        String email = request.getParameter("email");

        boolean is_email_valid = false;
        String email_error_message = "";
        if (email != null) {
            // Email is specified as request parameter, do the business logic here.
            if (email.trim().isEmpty()) {
                is_email_valid = false;
                email_error_message = "Это обязательное поле";
            } else if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                is_email_valid = false;
                session.setAttribute("currentAdress", email);
                email_error_message = "Введите адрес электронной почты";
            } else {
                try {
                    javax.mail.internet.InternetAddress ia = new javax.mail.internet.InternetAddress(email);
                    ia.validate();
                    is_email_valid = true;
                } catch (javax.mail.internet.AddressException ae) {
                    is_email_valid = false;
                    session.setAttribute("currentAdress", email);
                    email_error_message = "Проверьте адрес электронной почты";
                }
            }
        }

        if (is_email_valid) {

//            Subscribers man = getServiceLocator();
//            man.setEmail(email);
//
//            persist(man);

            Connection conn = null;
            Statement stmt = null;
            ResultSet rs;

            try {

                String insertSubscriberSQL = "INSERT INTO subscribers (email, created, ip_adress, ab_page) VALUES ('"
                        + email + "', NOW()"
                        + //                        "STR_TO_DATE(@dateUpdated, '%m/%d/%Y %h:%i:%s %p')" +
                        //                        DateFormat.getDateInstance(DateFormat.LONG).format(created.) + 
                        ", '" + request.getRemoteAddr() + "', 1);";
                String selectEmailSQL = "SELECT id FROM subscribers WHERE email = '" + email + "';";

                conn = jdbcLPDB.getConnection();
                stmt = conn.createStatement();

                rs = stmt.executeQuery(selectEmailSQL);
                if (rs.first()) {
                    session.setAttribute("currentAdress", email);
                    email_error_message = "Подписка на указанный адрес уже оформлена.";
                } else {

                    int res = stmt.executeUpdate(insertSubscriberSQL);
                    if (res > 0) {
                        String emailSubject = "Документ про рынок прогнозов";
                        String emailText;
//                        StringBuilder sb = new StringBuilder();
//
//                        sb.append("Здравствуйте!\n");
//                        sb.append("Это письмо от сервиса Moneypoolator\n");
//                        sb.append("Мы прислали вам документ, в котором объяснены принципы работы рынка прогнозов.\n");
//                        sb.append("Еще раз спасибо, что дали возможность рассказать о нас.\n");
//                        sb.append("Связаться с нами: support@moneypoolator.com\n");

                        emailText = getEmailText("http://moneypoolator.ru");


                        String emailFromAddress = "support@moneypoolator.com";
                        String emailFromPersonal = "Moneypoolator support team";
                        String attachFileName = getServletContext().getRealPath("/pdf/moneypoolator31.pdf");
                        String attachFilePsevdonim = "moneypoolator31.pdf";

                        String goback = request.getParameter("goback");
                        if (goback != null) {
                            if (goback.contains("landing31") || goback.contains("index")) {
                                attachFileName = getServletContext().getRealPath("/pdf/moneypoolator31.pdf");
                                attachFilePsevdonim = "moneypoolator31.pdf";
                            } else if (goback.contains("landing32")) {
                                attachFileName = getServletContext().getRealPath("/pdf/moneypoolator32.pdf");
                                attachFilePsevdonim = "moneypoolator32.pdf";
                            }
                        }

                        tlsSender.send(emailSubject, emailText, emailFromAddress, emailFromPersonal, email, attachFileName, attachFilePsevdonim);
                        //sslSender.send(emailSubject, emailText, emailFromAddress, emailFromPersonal, email, attachFileName);

                        email_error_message = "Поздравляем, ваш адрес зарегистрирован! Приглашение будет отправлено на указанный адрес.";
                    }
                }

            } catch (Exception e) {

                //out.println("Login Failed! Database error! Invalid user login or password!<br/>");
                throw new ServletException(e.getMessage());

            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException sqle) {
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

        session.setAttribute("mailErrorResponse", email_error_message);

        String goback = request.getParameter("goback");
        if (goback == null) {
            goback = "/index";
        }
        response.sendRedirect(response.encodeRedirectURL(contextPath + goback));
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

    private DataSource getJdbcLPDB() {
        return jdbcLPDB;
    }

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    protected String getEmailText(String context) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>\n");
        sb.append("<head>\n");
        sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\n");
        sb.append("<title>Документ про рынок прогнозов</title>\n");
        sb.append("<style>\n");
        sb.append(".mail-us {color: #ECBB00;}\n");
//        sb.append(".invite-friends {\n");
//        sb.append("background: #ECBB00;\n");
//        sb.append("font-weight: bold;\n");
//        sb.append("font-size: 14px;\n");
//        sb.append("border-radius: 5px;\n");
//        sb.append("border-style: none;\n");
//        sb.append("color: black;\n");
//        sb.append("text-decoration: none;\n");
//        sb.append("padding: 10px;\n");
//        sb.append("}\n");
//        sb.append(".invite-friends-span {color: #000001 !important;}\n");
        sb.append("</style>\n");
        sb.append("</head>\n");
        sb.append("<body style=\"font-family: arial; background: #E2DFD8;\">\n");
        sb.append("<div style=\"background: #E2DFD8; width: 100%; height: 100%;\">\n");
        sb.append("<table align=\"center\" style=\"width: 600px; -collapse: collapse;\">\n");
        sb.append("<tr style=\"background: #E2DFD8;\">\n");
        sb.append("<td style=\"display: inline; float: left; padding: 5px 0 5px 20px;\">\n");
        sb.append("<table style=\"width: 580px\">\n");
        sb.append("<tr>\n");
        sb.append("<td>\n");
        sb.append("<img src=\"").append(context).append("/_img3/logo2.png\" alt=\"Moneypoolator logo\"/>\n");
        sb.append("</td>\n");
        sb.append("</tr>\n");
        sb.append("</table>\n");
        sb.append("</td>\n");
        sb.append("</tr>\n");
        sb.append("<tr style=\"background: #E2DFD8\">\n");
        sb.append("<td style=\"background: white; width: 600px; padding: 30px 0 0 20px\">\n");
        sb.append("<table>\n");
        sb.append("<tr>\n");
        sb.append("<td>\n");
        sb.append("<p style=\"margin: 0; padding: 0; font-size: 24px; font-family: arial;\"><b>Здравствуйте!</b></p>\n");
        sb.append("</td>\n");
        sb.append("</tr>\n");
        sb.append("<tr>\n");
        sb.append("<td style=\"padding: 10px 10px 0 0;\">\n");
        sb.append("<p class=\"description\" style=\"font-size: 15px; font-family: arial; line-height: 20px; margin: 0; padding: 0;\">\n");
        sb.append("Это письмо от сервиса Moneypoolator. Мы прислали вам документ, в котором объяснены принципы работы рынка прогнозов.\n");
        sb.append("</p>\n");
        sb.append("</td>\n");
        sb.append("</tr>\n");
        sb.append("<tr>\n");
        sb.append("<td style=\"padding: 10px 10px 0 0;\">\n");
        sb.append("<p class=\"description\" style=\"font-size: 15px; font-family: arial; line-height: 20px; margin: 0; padding: 0;\">\n");
        sb.append("Еще раз спасибо, что дали возможность рассказать о нас.\n");
        sb.append("</p>\n");
        sb.append("</td>\n");
        sb.append("</tr>\n");
        sb.append("<tr>\n");
        sb.append("<td style=\"padding: 10px 10px 0 0;\">\n");
        sb.append("<p><br/></p>\n");
        sb.append("</td>\n");
        sb.append("</tr>\n");
        sb.append("</table>\n");
        sb.append("</td>\n");
        sb.append("</tr>\n");
        sb.append("</table>\n");
        sb.append("<table align=\"center\" style=\"width: 600px; border-collapse: collapse;\">\n");
        sb.append("<tr style=\"background: #E2DFD8\">\n");
        sb.append("<td style=\"background: #3E3633; padding: 0 20px 25px 20px;\">\n");
        sb.append("<p style=\"line-height: 20px; margin: 0; color: white; font-weight: bold; font-family: arial; font-size: 18px; border-top: 1px solid #706965; padding: 20px 0 0 0;\">\n");
        sb.append("Связаться с нами: \n");
        sb.append("<a style=\"line-height: 20px; margin: 0; font-weight: bold; font-family: arial; font-size: 18px; color: #ECBB00 !important; text-decoration: underline;\" href=\"mailto:support@moneypoolator.com\">\n");
        sb.append("<span class=\"mail-us\">support@moneypoolator.com</span>\n");
        sb.append("</a>\n");
        sb.append("</p>\n");
        sb.append("</td>\n");
        sb.append("</tr>\n");
        sb.append("<tr style=\"background: #E2DFD8\">\n");
        sb.append("<td style=\"padding: 0 0 20px 20px;\">\n");
        sb.append("<p style=\"margin: 0; font-size: 12px; font-family: arial; color: #787371\">\n");
        sb.append("&copy; 2012 &laquo;Moneypoolator &raquo; &mdash; рынки предсказаний\n");
        sb.append("</p>\n");
        sb.append("</td>\n");
        sb.append("</tr>\n");
        sb.append("</table>\n");
        sb.append("</div>\n");
        sb.append("</body>\n");
        sb.append("</html>\n");
        return sb.toString();
    }
}
