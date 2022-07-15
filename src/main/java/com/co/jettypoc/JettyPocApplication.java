package com.co.jettypoc;

import com.co.jettypoc.server.JettyPocServer;

public class JettyPocApplication {

  private static JettyPocServer jettyServer;

  public static void main(String[] args) throws Exception {    
    jettyServer = new JettyPocServer();
    jettyServer.start();
  }

}
