package controller.exceptions;

public class InvalidNameEnteredException extends RuntimeException {

  public InvalidNameEnteredException() {
    super();

  }

  public InvalidNameEnteredException(String message, Throwable cause) {
    super(message, cause);
  }


  @Override
  public String getMessage() {
    return "Names can only contain letters";
  }

}
