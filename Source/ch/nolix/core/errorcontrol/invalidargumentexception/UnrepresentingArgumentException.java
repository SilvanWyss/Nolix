//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link UnrepresentingArgumentException} is a
 * {@link InvalidArgumentException} that is supposed to be thrown when a given
 * argument does undesirable not represent an object of a certain type.
 * 
 * @author Silvan Wyss
 * @date 2017-03-05
 */
@SuppressWarnings("serial")
public final class UnrepresentingArgumentException extends InvalidArgumentException {

  // constant
  private static final String PRONOUN_A = "a";

  // constant
  private static final String PRONOUN_AN = "an";

  // static method
  /**
   * @param argument
   * @param type
   * @return a new {@link UnrepresentingArgumentException} for the given argument
   *         and type.
   * @throws IllegalArgumentException if the given type is null.
   */
  public static UnrepresentingArgumentException forArgumentAndType(final Object argument, final Class<?> type) {
    return new UnrepresentingArgumentException(argument, type);
  }

  // static method
  /**
   * @param argumentName
   * @param argument
   * @param type
   * @return a new {@link UnrepresentingArgumentException} for the given
   *         argumentName, argument and type.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   * @throws IllegalArgumentException if the given type is null.
   */
  public static UnrepresentingArgumentException forArgumentNameAndArgumentAndType(
      final String argumentName,
      final Object argument,
      final Class<?> type) {
    return new UnrepresentingArgumentException(argumentName, argument, type);
  }

  // static method
  /**
   * @param type
   * @return the name of the given type.
   * @throws IllegalArgumentException if the given type is null.
   */
  private static String getNameOfType(final Class<?> type) {

    // Asserts that the given type is not null.
    if (type == null) {
      throw new IllegalArgumentException("The given type is null.");
    }

    return type.getSimpleName();
  }

  // static method
  /**
   * @param noun
   * @return the pronoun for the given noun.
   * @throws IllegalArgumentException if the given noun is null or blank.
   */
  private static String getPronounForNoun(final String noun) {

    // Asserts that the given noun is not null.
    if (noun == null) {
      throw new IllegalArgumentException("The given noun is null.");
    }

    // Asserts that the given noun is not blank.
    if (noun.isBlank()) {
      throw new IllegalArgumentException("The given noun is blank.");
    }

    // Enumerates the first character of the given noun.
    return switch (noun.charAt(0)) {
      case
          'A',
          'a',
          'E',
          'e',
          'I',
          'i',
          'O',
          'o',
          'U',
          'u' ->
        PRONOUN_AN;
      default ->
        PRONOUN_A;
    };
  }

  // static method
  /**
   * @param type
   * @return the type name with the pronoun for the given type.
   * @throws IllegalArgumentException if the given type is null.
   */
  private static String getTypeNameWithPronounOfType(final Class<?> type) {

    final var typeName = getNameOfType(type);

    return (getPronounForNoun(typeName) + " " + typeName);
  }

  // constructor
  /**
   * Creates a new {@link UnrepresentingArgumentException} for the given argument
   * and type.
   * 
   * @param argument
   * @param type
   * @throws IllegalArgumentException if the given type is null.
   */
  private UnrepresentingArgumentException(final Object argument, final Class<?> type) {

    // Calls constructor of the base class.
    super(argument, "does not represent " + getTypeNameWithPronounOfType(type));
  }

  // constructor
  /**
   * Creates a new {@link UnrepresentingArgumentException} for the given
   * argumentName, argument and type.
   * 
   * @param argumentName
   * @param argument
   * @param type
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   * @throws IllegalArgumentException if the given type is null.
   */
  private UnrepresentingArgumentException(final String argumentName, final Object argument, final Class<?> type) {

    // Calls constructor of the base class.
    super(argumentName, argument, "does not represent " + getTypeNameWithPronounOfType(type));
  }
}
