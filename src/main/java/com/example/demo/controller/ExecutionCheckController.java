package com.example.demo.controller;

import com.example.demo.modules.DateExecutedChecks;
import com.example.demo.modules.ExecutedCheckOutput;
import com.example.demo.services.ExecutionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;

/**
 * Controller for the execution of the checks - defines the API for getting all executed checks,
 * their status and the values related to each check.
 */
@RestController
@RequestMapping("/executedChecks") // change later
@CrossOrigin(CorsConfiguration.ALL)
public class ExecutionCheckController {

  @Autowired private ExecutionCheckService executionCheckService;

  /**
   * Serves as an end point for the FE to get all executed checks.
   *
   * @return list of entries, where each entry has as a Key the string ``output", and the value of
   *     the entry is the status of the check - passed, failed, attention point, the check itself
   *     and the attributeValue that is being checked
   * @throws IOException if the parsing of the data fails in the Service class
   */
  @PostMapping("/all")
  public DateExecutedChecks executeChecksAll(
      @RequestBody String input) throws IOException {
    return executionCheckService.filterDataWithChecks(input);
  }
}
