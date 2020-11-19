package controller.exceptions;

public class InvalidNumberPlayersException extends Exception {


  public InvalidNumberPlayersException() {
    super();

  }

  public InvalidNumberPlayersException(String message, Throwable cause) {
    super(message, cause);
  }


  @Override
  public String getMessage() {
    return "The number of Autoplayers must be between 1 and 7";
  }
}

