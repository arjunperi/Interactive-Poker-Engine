package controller.exceptions;

public class InvalidNameEnteredException extends RuntimeException {

  public InvalidNameEnteredException() {
    super();
  }

  @Override
  public String getMessage() {
    return "Names can only contain letters";
  }

}
