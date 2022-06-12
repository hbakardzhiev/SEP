package com.example.demo;

import org.springframework.security.core.context.SecurityContextHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/** Helper class where constants and helper functions are defined. */
public class Util {

  // TODO the swap out this hardcoded url once we get access to web based WindChill
  public static final String CHANGE_NOTICE_EXAMPLE_HTML = "Change Notice - Example.html";

  public static final String RESOURCE_LOCATION = "/home/sep/backend/src/main/resources/";
  /**
   * Reads the input stream and builds a string out of it.
   *
   * @param inputStream The stream of file contents to be read
   * @return string content of the input file
   * @throws IOException
   */
  public static String readFromInputStream(InputStream inputStream) throws IOException {
    StringBuilder resultStringBuilder = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = br.readLine()) != null) {
        resultStringBuilder.append(line).append("\n");
      }
    }
    return resultStringBuilder.toString();
  }

  public static String getUsernameFromPrincipal() {
    final var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    final var username = principal.toString();
    return username;
  }
}
