package com.example.demo.modules;

import java.time.OffsetDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

/**
 * This class encapsulates the data types that needs to be sent to the FE with regard of the
 * execution of the checks. It saves the inputValue - value that is checked, and the object
 * checkAndActionName which is the check and the action of the check that are used for checking.
 */
@Data
@AllArgsConstructor
public class ExecutedCheckOutput {
  private Result status;
  private String inputValue; // the value that a specific attribute has in Windchill
  private CheckAndActionName checkAndActionName;

  private OffsetDateTime dateTime;

  public ExecutedCheckOutput(
      Result status, String inputValue, CheckAndActionName checkAndActionName) {
    this.status = status;
    this.inputValue = inputValue;
    this.checkAndActionName = checkAndActionName;
  }

  @Override
  public String toString() {
    return "CheckInputValue{"
        + "status='"
        + status
        + '\''
        + ", inputValue='"
        + inputValue
        + '\''
        + ", checkAndActionName="
        + checkAndActionName
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ExecutedCheckOutput that = (ExecutedCheckOutput) o;
    return Objects.equals(status, that.status)
        && Objects.equals(inputValue, that.inputValue)
        && Objects.equals(checkAndActionName, that.checkAndActionName);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
