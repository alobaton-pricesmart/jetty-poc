package com.co.jettypoc.server;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import com.co.jettypoc.properties.JettyPocConstants;
import com.co.jettypoc.properties.JettyPocProperties;
import com.co.jettypoc.servlet.HealthCheckServlet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JettyPocServer {
  private Server server;

  public void start() throws Exception {
    JettyPocProperties.loadProperties();
    
    int minThreads = JettyPocProperties.getInt(JettyPocConstants.THREAD_POOL_MIN_THREADS);
    int maxThreads = JettyPocProperties.getInt(JettyPocConstants.THREAD_POOL_MAX_THREADS);
    int idleTimeout = JettyPocProperties.getInt(JettyPocConstants.THREAD_POOL_IDLE_TIMEOUT);
    QueuedThreadPool threadPool = new QueuedThreadPool(maxThreads, minThreads,
        idleTimeout);

    server = new Server(threadPool);
    
    ServerConnector connector = new ServerConnector(server);
    int port = JettyPocProperties.getInt(JettyPocConstants.SERVER_PORT);
    connector.setPort(port);
    
    server.setConnectors(new Connector[]{connector});

    ServletHandler servletHandler = new ServletHandler();
    server.setHandler(servletHandler);

    servletHandler.addServletWithMapping(HealthCheckServlet.class, "/status");

    server.start();
    log.info("JettyPocServer started.");
  }

  public void stop() throws Exception {
    server.stop();
  }
}
