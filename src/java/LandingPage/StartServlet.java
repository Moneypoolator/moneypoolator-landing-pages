package LandingPage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
public class StartServlet extends HttpServlet {

    @Resource(name = "jdbc/LPDB")
    private DataSource jdbcLPDB;

    @Override
    public void init() throws ServletException {
        super.init();

//        try {
//            Context env;
//            env = (Context) new InitialContext().lookup("java:comp/env");
//            jdbcLPDB = (DataSource) env.lookup("jdbc/LPDB");
//
//            if (jdbcLPDB == null) {
//                throw new ServletException("'jdbc:mysql://localhost:3306/landing_page' is in unknown DataSource");
//            }
//        } catch (NamingException ne) {
//            throw new ServletException(ne.getMessage());
//        }
    }

    /*
     public static Connection getConnect()
     {
     Connection conn = null;
		
     String jndiContext = "java:/comp/env/jdbc/sklad";
		
     try{
     InitialContext ic = new InitialContext();
			
     DataSource ds = (DataSource) ic.lookup(jndiContext);
			
     if (ds == null)
     {
     throw new SQLException("Data source not found :"+jndiContext);
     }
			
     conn = (Connection) ds.getConnection();
			
     }catch (NamingException e) {
     e.printStackTrace();
     } catch (SQLException e) {
     e.printStackTrace();
     }
     return conn;
     }
     */
    
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

        log("Start processRequest");

        try {
            if (jdbcLPDB == null) {

                log("Get DataSource context");

                // Obtain our environment naming context
                Context initCtx = new InitialContext();
                Context envCtx = (Context) initCtx.lookup("java:comp/env");

                // Look up our data source
                jdbcLPDB = (DataSource) envCtx.lookup("jdbc/LPDB");

                if (jdbcLPDB == null) {
                    
                    log("Invalid DataSource context");

                    throw new ServletException("'jdbc:mysql://localhost:3306/landing_page' is in unknown DataSource");
                } else {
                    log("DataSource context -- Complete:" + jdbcLPDB.getClass().toString());
                }
            }
        } catch (NamingException ne) {
            throw new ServletException(ne.getMessage());
        }

        String contextPath = request.getContextPath();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs;

        int subscribersCount = 437;
        String subscribersSuffix = "человек";

        try {

            String selectMaxIdSQL = "select max(subscribers.id) from subscribers";

            conn = jdbcLPDB.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectMaxIdSQL);

            if (rs.first()) {
                subscribersCount = rs.getInt(1);

                String id = rs.getString(1);

                String last_symbol = id.substring(id.length() - 1);
                Integer last_number = Integer.valueOf(last_symbol);

                String prev_last_symbol = "0";
                if (id.length() > 1) {
                    prev_last_symbol = id.substring(id.length() - 2);
                }
                Integer prev_last_number = Integer.valueOf(prev_last_symbol);

                if ((last_number > 1 && last_number < 5) && (prev_last_number != 1)) {
                    subscribersSuffix = "человека";
                } else {
                    subscribersSuffix = "человек";
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

        request.setAttribute("subscribersCount", subscribersCount);
        request.setAttribute("subscribersSuffix", subscribersSuffix);
        RequestDispatcher dispatcher = request.getRequestDispatcher(/*contextPath +*/"/index.jsp");
        dispatcher.forward(request, response);

//        HttpSession session = request.getSession();
//        session.setAttribute("subscribersCount", subscribersCount);
//        session.setAttribute("subscribersSuffix", subscribersSuffix);
//        response.sendRedirect(response.encodeRedirectURL(contextPath + "/index.jsp"));

        /*
         response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
         try {
            
         out.println("<!DOCTYPE html>");
         out.println("<html>");
         out.println("<head>");
         out.println("<title>Servlet StartServlet</title>");
         out.println("</head>");
         out.println("<body>");
         out.println("<h1>Servlet StartServlet at " + request.getContextPath() + "</h1>");
         out.println("</body>");
         out.println("</html>");
         } finally {
         out.close();
         }
         */
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
}
