/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LandingPage;

import LandingApp.AppAsyncListener;
import LandingApp.AsyncRequestProcessor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadPoolExecutor;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 1
 */
@WebServlet(asyncSupported = true)
//@WebServlet(urlPatterns = "/AsyncEmailSenderServlet", asyncSupported = true)
public class AsyncEmailSenderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static Sender tlsSender = new Sender(Sender.TransportLayer.TLS, "rul@moneypoolator.com", "e5U86J45PR_");
    private static Sender sslSender = new Sender(Sender.TransportLayer.SSL, "keeper@moneypoolator.com", "intheairtonight");

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
        long startTime = System.currentTimeMillis();
        System.out.println("AsyncLongRunningServlet Start::Name="
                + Thread.currentThread().getName() + "::ID="
                + Thread.currentThread().getId());

        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);

        String time = request.getParameter("time");
        int secs = 1000;
        try {
            secs = Integer.valueOf(time);
            // max 10 seconds
            if (secs > 10000) {
                secs = 10000;
            }
        } catch (NumberFormatException e) {
        }


        String email = request.getParameter("email");

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

        tlsSender.config(emailSubject, emailText, emailFromAddress, emailFromPersonal, email, attachFileName, attachFilePsevdonim);
        //tlsSender.send(emailSubject, emailText, emailFromAddress, emailFromPersonal, email, attachFileName, attachFilePsevdonim);
        //sslSender.send(emailSubject, emailText, emailFromAddress, emailFromPersonal, email, attachFileName);



        AsyncContext asyncCtx = request.startAsync();
        asyncCtx.addListener(new AppAsyncListener());
        asyncCtx.setTimeout(9000);

        ThreadPoolExecutor executor = (ThreadPoolExecutor) request
                .getServletContext().getAttribute("executor");

        executor.execute(new AsyncRequestProcessor(asyncCtx, tlsSender));
        long endTime = System.currentTimeMillis();
        System.out.println("AsyncLongRunningServlet End::Name="
                + Thread.currentThread().getName() + "::ID="
                + Thread.currentThread().getId() + "::Time Taken="
                + (endTime - startTime) + " ms.");


        //String goback = request.getParameter("goback");
//        if (goback == null) {
//            goback = "/index";
//        }
        
        String contextPath = request.getContextPath();
        response.sendRedirect(response.encodeRedirectURL(contextPath + "/thanks"));

        //        response.sendRedirect(response.encodeRedirectURL(contextPath + goback));

//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        try {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet AsyncEmailSenderServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet AsyncEmailSenderServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        } finally {
//            out.close();
//        }
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

    protected String getEmailText(String context) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>\n");
        sb.append("<head>\n");
        sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\n");
        sb.append("<title>Документ про рынок прогнозов</title>\n");
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
        sb.append("<span style=\"color: #ECBB00;\">support@moneypoolator.com</span>\n");
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

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res); //To change body of generated methods, choose Tools | Templates.
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
