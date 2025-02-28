package ch.nolix.core.errorcontrol.errormapping;

import ch.nolix.coreapi.errorcontrolapi.errormappingapi.IErrorMessageExtractor;

/**
 * @author Silvan Wyss
 * @version 2025-02-28
 */
public final class ErrorMessageExtractor implements IErrorMessageExtractor {

  private static final String DEFAULT_ERROR_MESSAGE = "An error occured.";

  /**
   * {@inheritDoc}
   */
  @Override
  public String getMessageOfError(final Throwable error) {

    if (error == null) {
      return DEFAULT_ERROR_MESSAGE;
    }

    final var message = error.getMessage();

    if (message == null || message.isBlank()) {
      return DEFAULT_ERROR_MESSAGE;
    }

    return message;
  }
}
