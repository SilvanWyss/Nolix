package ch.nolix.core.errorcontrol.exception;

@SuppressWarnings("serial")
public final class WrapperException extends RuntimeException {

  public static final String DEFAULT_ERROR_MESSAGE = "An error occured.";

  private WrapperException(final Throwable error) {
    this(DEFAULT_ERROR_MESSAGE, error);
  }

  private WrapperException(final String errorMessage, final Throwable error) {
    super(errorMessage, error);
  }

  public static WrapperException forError(final Throwable error) {
    return new WrapperException(error);
  }

  public static WrapperException forErrorMessageAndError(final String errorMessage, final Throwable error) {
    return new WrapperException(errorMessage, error);
  }
}
