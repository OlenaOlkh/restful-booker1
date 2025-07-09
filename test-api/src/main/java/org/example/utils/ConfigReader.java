package org.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class for reading values from application.properties.
 */
public class ConfigReader {

  private static final Properties properties = new Properties();

  static {
    try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
      if (input == null) {
        throw new RuntimeException("Cannot find application.properties file");
      }
      properties.load(input);
    } catch (IOException e) {
      throw new RuntimeException("Failed to load application.properties", e);
    }
  }

  /**
     * Returns the value for a given property key.
     *
     * @param key the property key
     * @return the corresponding property value
     */
  public static String get(String key) {
    return properties.getProperty(key);
  }
}
