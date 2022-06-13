package com.example.demo.modules;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.AbstractMap;
import java.util.List;

@Data // establishes getters and setters
@AllArgsConstructor
public class DateExecutedChecks {

  private OffsetDateTime date;
  private List<AbstractMap.SimpleEntry<String, ExecutedCheckOutput>> executedChecks;
}
