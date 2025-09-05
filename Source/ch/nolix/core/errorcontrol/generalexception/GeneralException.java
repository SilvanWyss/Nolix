package ch.nolix.core.errorcontrol.generalexception;

@SuppressWarnings("serial")
public final class GeneralException extends RuntimeException {
  private GeneralException(final String errorMessage) {
    super(getValidErroMessageOfErrorMessage(errorMessage));
  }

  public static GeneralException withErrorMessage(final String errorMessage) {
    return new GeneralException(errorMessage);
  }

  private static String getValidErroMessageOfErrorMessage(final String errorMessage) {
    if (errorMessage == null) {
      throw new IllegalArgumentException("The given error message is null.");
    }

    if (errorMessage.isBlank()) {
      throw GeneralException.withErrorMessage("The given error message is blank.");
    }

    return errorMessage;
  }

  @Override
  public boolean equals(final Object object) {
    if (object instanceof final GeneralException generalException) {
      return getMessage().equals(generalException.getMessage());
    }

    return false;
  }

  @Override
  public int hashCode() {
    return getMessage().hashCode();
  }
}
