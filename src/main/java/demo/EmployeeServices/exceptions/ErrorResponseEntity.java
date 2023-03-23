package demo.EmployeeServices.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ErrorResponseEntity {

  private String message;
  private String sqlState;

  public ErrorResponseEntity(String message, String sqlState) {
    this.message = message;
    this.sqlState = sqlState;
  }

  public ErrorResponseEntity(String message) {
    this.message = message;
  }
}
