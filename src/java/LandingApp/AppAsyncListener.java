/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LandingApp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletResponse;

/**
 * Web application lifecycle listener.
 *
 * @author 1
 */
public class AppAsyncListener implements AsyncListener {

    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        System.out.println("AppAsyncListener onComplete");
        // we can do resource cleanup activity here
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        System.out.println("AppAsyncListener onTimeout");
        //we can send appropriate response to client
        ServletResponse response = event.getAsyncContext().getResponse();
        PrintWriter out = response.getWriter();
        out.write("TimeOut Error in Processing");
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        System.out.println("AppAsyncListener onError");
        //we can return error response to client
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        System.out.println("AppAsyncListener onStartAsync");
        //we can log the event here
    }

    
}
