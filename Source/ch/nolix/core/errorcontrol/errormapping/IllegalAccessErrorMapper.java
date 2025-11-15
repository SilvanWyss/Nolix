package ch.nolix.core.errorcontrol.errormapping;

/**
 * Of the {@link IllegalAccessErrorMapper} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2025-11-15
 */
public final class IllegalAccessErrorMapper {

  /**
   * Prevents that an instance of the {@link IllegalAccessErrorMapper} can be
   * created.
   */
  private IllegalAccessErrorMapper() {
  }

  /**
   * @param illegalAccessException
   * @return a new {@link IllegalAccessError} for the given
   *         illegalAccessException.
   * @throws RuntimeException if the given illegalAccessException is null.
   */
  public static IllegalAccessError mapIllegalAccessExceptionToIllegalAccessError(
    final IllegalAccessException illegalAccessException) {
    final var message = illegalAccessException.getMessage();

    if (message == null || message.isBlank()) {
      throw new IllegalAccessError();
    }

    throw new IllegalAccessError(message);
  }
}
