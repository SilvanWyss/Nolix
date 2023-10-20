//own imports
package ch.nolix.core.errorcontrol.exception;

//class
@SuppressWarnings("serial")
public final class GeneralException extends RuntimeException {

  //constructor
  private GeneralException(final String errorMessage) {
    super(getValidErroMessageOfErrorMessage(errorMessage));
  }

  //static method
  public static GeneralException withErrorMessage(final String errorMessage) {
    return new GeneralException(errorMessage);
  }

  //static method
  private static String getValidErroMessageOfErrorMessage(final String errorMessage) {

    if (errorMessage == null) {
      throw new IllegalArgumentException("The given error message is null.");
    }

    if (errorMessage.isBlank()) {
      throw GeneralException.withErrorMessage("The given error message is blank.");
    }

    return errorMessage;
  }

  //method
  @Override
  public boolean equals(final Object object) {

    if (object instanceof GeneralException generalException) {
      return getMessage().equals(generalException.getMessage());
    }

    return false;
  }

  //method
  @Override
  public int hashCode() {
    return getMessage().hashCode();
  }
}
