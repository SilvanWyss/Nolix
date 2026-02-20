/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.errorcontrol.errormapping;

import ch.nolix.baseapi.errorcontrol.errormapping.IErrorMessageExtractor;

/**
 * @author Silvan Wyss
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
