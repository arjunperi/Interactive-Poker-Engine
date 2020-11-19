package controller.exceptions;

/**
 * Exception class to throw when an exception occurs in the model class
 *
 * @author Arjun Peri
 */

public class ControllerException extends RuntimeException {

  /**
   * Allows throwing a ModelException with custom message.
   *
   * @param message
   */
  public ControllerException(String message) {
    super(message);
  }


  /**
   * Allows throwing a ModelException with custom message and cause.
   *
   * @param message
   */
  public ControllerException(String message, Throwable cause) {
    super(message, cause);
  }
}
