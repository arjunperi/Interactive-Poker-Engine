package controller.exceptions;

public class InvalidStartingAmountException extends RuntimeException {

  public InvalidStartingAmountException() {
    super();

  }

  public InvalidStartingAmountException(String message, Throwable cause) {
    super(message, cause);
  }


  @Override
  public String getMessage() {
    return "Starting amount must be integer between 50 and 10,000";
  }
}

