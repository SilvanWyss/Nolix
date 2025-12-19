package ch.nolix.core.errorcontrol.validation;

import ch.nolix.coreapi.errorcontrol.validation.IValidationResponse;

/**
 * @author Silvan Wyss
 */
public final class ValidationResponse implements IValidationResponse {
  public static final ValidationResponse OK_VALIDATION_RESPONSE = new ValidationResponse(true, "Ok");

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

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public boolean isOk() {
    return ok;
  }
}
