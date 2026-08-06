/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.response;

import ch.nolix.baseapi.validation.response.IValidationResponse;
import ch.nolix.template.webgui.textcatalog.TextCatalog;

/**
 * @author Silvan Wyss
 */
public final class ValidationResponse implements IValidationResponse {
  public static final ValidationResponse OK_VALIDATION_RESPONSE = new ValidationResponse(true, TextCatalog.OK);

  private final boolean ok;

  private final String message;

  private ValidationResponse(final boolean ok, final String message) {
    if (message == null) {
      throw new IllegalArgumentException("The given message is null.");
    }

    if (message.isBlank()) {
      throw new IllegalArgumentException("The given message is blank.");
    }

    this.ok = ok;
    this.message = message;
  }

  public static ValidationResponse createNotOkValidationResponseWithMessage(final String message) {
    return new ValidationResponse(false, message);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getMessage() {
    return message;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isOk() {
    return ok;
  }
}
