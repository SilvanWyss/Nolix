package ch.nolix.core.errorcontrol.invalidargumentexception;

/**
 * A {@link UnsupportedCaseException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument does support a certain case.
 * 
 * @author Silvan Wyss
 * @version 2021-03-23
 */
@SuppressWarnings("serial")
public final class UnsupportedCaseException extends InvalidArgumentException {

  private static final String ARGUMENT_NAME = "case";

  private static final String ERROR_PREDICATE = "is not supported";

  /**
   * Creates a new {@link UnsupportedCaseException} for the given pCase.
   *
   * @param pCase
   * @throws IllegalArgumentException if the given pCase is null.
   */
  private UnsupportedCaseException(final Enum<?> pCase) {

    //Calls constructor of the base class.
    super((Object) getNameOfCase(pCase), ARGUMENT_NAME, ERROR_PREDICATE);
  }

  /**
   * Creates a new {@link UnsupportedCaseException} for the given pCase.
   *
   * @param pCase
   * @throws IllegalArgumentException if the given pCase is null.
   * @throws IllegalArgumentException if the given pCase is blank.
   */
  private UnsupportedCaseException(final String pCase) {

    //Calls constructor of the base class.
    super((Object) getValidCaseOfCase(pCase), ARGUMENT_NAME, ERROR_PREDICATE);
  }

  /**
   * @param pCase
   * @return a new {@link UnsupportedCaseException} for the given pCase.
   * @throws IllegalArgumentException if the given pCase is null.
   */
  public static UnsupportedCaseException forCase(final Enum<?> pCase) {
    return new UnsupportedCaseException(pCase);
  }

  /**
   * @param pCase
   * @return a new {@link UnsupportedCaseException} for the given pCase.
   * @throws IllegalArgumentException if the given pCase is null.
   * @throws IllegalArgumentException if the given pCase is blank.
   */
  public static UnsupportedCaseException forCase(final String pCase) {
    return new UnsupportedCaseException(pCase);
  }

  /**
   * @param pCase
   * @return the name of the given pCase.
   * @throws IllegalArgumentException if the given pCase is null.
   */
  private static String getNameOfCase(final Enum<?> pCase) {

    //Asserts that the given case is not null.
    if (pCase == null) {
      throw new IllegalArgumentException("The given case is null.");
    }

    return pCase.toString();
  }

  /**
   * @param pCase
   * @return a valid case of the given pCase.
   * @throws IllegalArgumentException if the given pCase is null.
   * @throws IllegalArgumentException if the given pCase is blank.
   */
  private static String getValidCaseOfCase(final String pCase) {

    //Asserts that the given case is not null.
    if (pCase == null) {
      throw new IllegalArgumentException("The given case is null.");
    }

    //Asserts that the given case is not blank.
    if (pCase.isBlank()) {
      throw new IllegalArgumentException("The given case is blank.");
    }

    return pCase;
  }
}
