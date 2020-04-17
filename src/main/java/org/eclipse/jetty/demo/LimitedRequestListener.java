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
        long requests = request.getHttpChannel().getRequests();
        // After X responses, forcibly set connection close on response
        if (requests >= maxRequests)
        {
            request.getResponse().setHeader("Connection", "close");
            LOG.debug("Setting [Connection: Close] on Request #{} for {}", requests, request.getHttpChannel().getEndPoint().getTransport());
        }
    }
}
