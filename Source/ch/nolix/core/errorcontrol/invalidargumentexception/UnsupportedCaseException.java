package ch.nolix.core.errorcontrol.invalidargumentexception;

/**
 * A {@link UnsupportedCaseException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument is undesirable not a supported
 * case.
 * 
 * @author Silvan Wyss
 * @version 2021-03-23
 */
@SuppressWarnings("serial")
public final class UnsupportedCaseException extends InvalidArgumentException {

  private static final String ARGUMENT_NAME = "case";

  private static final String ERROR_PREDICATE = "is not supported";

  /**
   * Creates a new {@link UnsupportedCaseException} for the given paramCase.
   *
   * @param paramCase
   * @throws IllegalArgumentException if the given paramCase is null.
   */
  private UnsupportedCaseException(final Enum<?> paramCase) {
    super((Object) getNameOfCase(paramCase), ARGUMENT_NAME, ERROR_PREDICATE);
  }

  /**
   * Creates a new {@link UnsupportedCaseException} for the given paramCase.
   *
   * @param paramCase
   * @throws IllegalArgumentException if the given paramCase is null or blank.
   */
  private UnsupportedCaseException(final String paramCase) {
    super((Object) getNameOfCase(paramCase), ARGUMENT_NAME, ERROR_PREDICATE);
  }

  /**
   * @param paramCase
   * @return a new {@link UnsupportedCaseException} for the given paramCase.
   * @throws IllegalArgumentException if the given paramCase is null.
   */
  public static UnsupportedCaseException forCase(final Enum<?> paramCase) {
    return new UnsupportedCaseException(paramCase);
  }

  /**
   * @param paramCase
   * @return a new {@link UnsupportedCaseException} for the given paramCase.
   * @throws IllegalArgumentException if the given pCase is null or blank.
   */
  public static UnsupportedCaseException forCase(final String paramCase) {
    return new UnsupportedCaseException(paramCase);
  }

  /**
   * @param paramCase
   * @return the name of the given paramCase.
   * @throws IllegalArgumentException if the given paramCase is null.
   */
  private static String getNameOfCase(final Enum<?> paramCase) {

    if (paramCase == null) {
      throw new IllegalArgumentException("The given case is null.");
    }

    return paramCase.name();
  }

  /**
   * @param paramCase
   * @return the name of the given paramCase.
   * @throws IllegalArgumentException if the given paramCase is null or blank.
   */
  private static String getNameOfCase(String paramCase) {

    if (paramCase == null) {
      throw new IllegalArgumentException("The given case is null.");
    }

    if (paramCase.isBlank()) {
      throw new IllegalArgumentException("The given case is blank.");
    }

    return paramCase;
  }
}
