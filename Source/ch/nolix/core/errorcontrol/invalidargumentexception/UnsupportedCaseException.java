//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link UnsupportedCaseException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument does support a certain case.
 * 
 * @author Silvan Wyss
 * @date 2021-03-23
 */
@SuppressWarnings("serial")
public final class UnsupportedCaseException extends InvalidArgumentException {

  //constant
  private static final String ARGUMENT_NAME = "case";

  //constant
  private static final String ERROR_PREDICATE = "is not supported";

  //static method
  /**
   * @param pCase
   * @return a new {@link UnsupportedCaseException} for the given pCase.
   * @throws IllegalArgumentException if the given pCase is null.
   */
  public static UnsupportedCaseException forCase(final Enum<?> pCase) {
    return new UnsupportedCaseException(pCase);
  }

  //static method
  /**
   * @param pCase
   * @return a new {@link UnsupportedCaseException} for the given pCase.
   * @throws IllegalArgumentException if the given pCase is null.
   * @throws IllegalArgumentException if the given pCase is blank.
   */
  public static UnsupportedCaseException forCase(final String pCase) {
    return new UnsupportedCaseException(pCase);
  }

  //static method
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

  //static method
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

  //constructor
  /**
   * Creates a new {@link UnsupportedCaseException} for the given pCase.
   *
   * @param pCase
   * @throws IllegalArgumentException if the given pCase is null.
   */
  private UnsupportedCaseException(final Enum<?> pCase) {

    //Calls constructor of the base class.
    super(ARGUMENT_NAME, getNameOfCase(pCase), ERROR_PREDICATE);
  }

  //constructor
  /**
   * Creates a new {@link UnsupportedCaseException} for the given pCase.
   *
   * @param pCase
   * @throws IllegalArgumentException if the given pCase is null.
   * @throws IllegalArgumentException if the given pCase is blank.
   */
  private UnsupportedCaseException(final String pCase) {

    //Calls constructor of the base class.
    super(ARGUMENT_NAME, getValidCaseOfCase(pCase), ERROR_PREDICATE);
  }
}
