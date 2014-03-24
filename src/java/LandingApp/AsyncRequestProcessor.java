/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LandingApp;

import LandingPage.Sender;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;

public class AsyncRequestProcessor implements Runnable {

    private AsyncContext asyncContext;
    private Sender emailSender;
    private int secs;

    public AsyncRequestProcessor() {
    }

    public AsyncRequestProcessor(AsyncContext asyncCtx, Sender sender) {
        this.asyncContext = asyncCtx;
        this.emailSender = sender;
        this.secs = 1000;
    }

    @Override
    public void run() {
        System.out.println("Async Supported? "
                + asyncContext.getRequest().isAsyncSupported());

        emailSender.send();

//        try {
//            longProcessing(secs);
//            PrintWriter out = asyncContext.getResponse().getWriter();
//            out.write("Processing done for " + secs + " milliseconds!!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //complete the processing
        asyncContext.complete();
    }

    private void longProcessing(int secs) {
        // wait for given time before finishing
        try {
            Thread.sleep(secs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
