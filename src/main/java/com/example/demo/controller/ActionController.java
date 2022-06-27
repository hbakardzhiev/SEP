package com.example.demo.controller;

import com.example.demo.modules.Action;
import com.example.demo.services.ActionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.cors.CorsConfiguration;

/** Defines the needed API for the table with the actions and value types. */
@AllArgsConstructor
@RestController
@RequestMapping("/action")
@CrossOrigin(CorsConfiguration.ALL)
public class ActionController {

  private ActionService actionService;

  /**
   * Retrieves all actions from the database.
   *
   * @return list of all actions in the database
   */
  @GetMapping
  public List<Action> getAllAction() {
    return actionService.findAll();
  }
}
