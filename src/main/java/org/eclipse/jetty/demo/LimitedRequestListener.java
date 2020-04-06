package org.eclipse.jetty.demo;

import org.eclipse.jetty.server.HttpChannel;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;

public class LimitedRequestListener implements HttpChannel.Listener
{
    private static final Logger LOG = Log.getLogger(LimitedRequestListener.class);
    private int maxRequests = 10;

    public int getMaxRequests()
    {
        return maxRequests;
    }

    public void setMaxRequests(int maxRequests)
    {
        this.maxRequests = maxRequests;
    }

    @Override
    public void onResponseBegin(Request request)
    {
        // After 10 responses, forcibly set connection close on response
        if (request.getHttpChannel().getRequests() >= maxRequests)
        {
            request.getResponse().setHeader("Connection", "close");
        }
    }

    @Override
    public void onComplete(Request request)
    {
        if (request.getHttpChannel().getRequests() > maxRequests)
        {
            // forcibly close connection after 10th request and response are complete
            request.getHttpChannel().getEndPoint().close();
        }
    }
}
