/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
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
                is_email_valid = true;
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
                        + email + "', NOW()" +
                        //                        "STR_TO_DATE(@dateUpdated, '%m/%d/%Y %h:%i:%s %p')" +
                        //                        DateFormat.getDateInstance(DateFormat.LONG).format(created.) + 
                        ", '"+ request.getRemoteAddr() +"', 1);";
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
        response.sendRedirect(response.encodeRedirectURL(contextPath + "/index"));
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

    @Override
    public void init() throws ServletException {
        super.init();

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
}
