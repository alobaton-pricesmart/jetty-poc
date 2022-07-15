package com.co.jettypoc.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class JettyPocProperties {

  private static Properties properties;

  public static void loadProperties() throws IOException {
    String path = "src/main/resources/application.properties";
    FileInputStream fis = new FileInputStream(path);
    properties = new Properties();
    properties.load(fis);
  }

  public static String getString(String key) {
    return properties.getProperty(key);
  }

  public static int getInt(String key) {
    return Integer.parseInt(properties.getProperty(key));
  }
}
