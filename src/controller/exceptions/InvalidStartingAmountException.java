package controller.exceptions;

public class InvalidStartingAmountException extends RuntimeException {

  public InvalidStartingAmountException() {
    super();

  }

  @Override
  public String getMessage() {
    return "Starting amount must be integer between 50 and 10,000";
  }
}
