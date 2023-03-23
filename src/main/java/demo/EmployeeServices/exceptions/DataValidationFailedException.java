package demo.EmployeeServices.exceptions;

public class DataValidationFailedException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public DataValidationFailedException(String errorMessage) {
    super(errorMessage);
  }
}
