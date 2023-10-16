//package declaration
package ch.nolix.core.errorcontrol.exception;

//class
@SuppressWarnings("serial")
public final class WrapperException extends RuntimeException {

  //constant
  public static final String DEFAULT_ERROR_MESSAGE = "An error occured.";

  //static method
  public static WrapperException forError(final Throwable error) {
    return new WrapperException(error);
  }

  //static method
  public static WrapperException forErrorMessageAndError(final String errorMessage, final Throwable error) {
    return new WrapperException(errorMessage, error);
  }

  //constructor
  private WrapperException(final Throwable error) {
    this(DEFAULT_ERROR_MESSAGE, error);
  }

  //constructor
  private WrapperException(final String errorMessage, final Throwable error) {
    super(errorMessage, error);
  }
}
