package com.example.demo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

/** Helper class where constants and helper functions are defined. */
public class Util {

  // TODO the swap out this hardcoded url once we get access to web based WindChill
  public static final String CHANGE_NOTICE_EXAMPLE_HTML = "Change Notice - Example.html";

  //  public static final String RESOURCE_LOCATION = "/home/sep/backend/src/main/resources/"; // for
  // testing on server
  public static final String RESOURCE_LOCATION = "src/main/resources/"; // for testing locally

  public static final String EXTERNAL_PAGE = "ExternalPage.html";
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

  /**
   * Retrieves the username of the admin who is logged in
   * @return the username of the admin
   */
  public static String getUsernameFromPrincipal() {
    final var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    final var username = principal.toString();
    return username;
  }

  /**
   * Creates access_token for the admin that has sent the request and encodes it
   * @param request the http request that was made (the url)
   * @param admin the admin who made the request
   * @return the encoded access_token
   */
  public static String getToken(HttpServletRequest request, User admin) {
    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

    // retrieving the token
    String access_token =
            JWT.create()
                    .withSubject(admin.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3 * 60 * 60 * 1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles", "ADMIN")
                    .sign(algorithm);
    return access_token;
  }

  /**
   * Verifies the token and decodes it.
   * @param token the token to be verified
   * @return decoded json web token
   */
  public static DecodedJWT getDecodedJWT(String token) {
    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
    JWTVerifier verifier = JWT.require(algorithm).build();
    DecodedJWT decodedJWT = verifier.verify(token);
    return decodedJWT;
  }
}
