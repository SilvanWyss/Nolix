package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrolapi.exceptionargumentboxapi.ErrorPredicateDto;

/**
 * A {@link UnrepresentingArgumentException} is a
 * {@link InvalidArgumentException} that is supposed to be thrown when a given
 * argument does undesirable not represent an object of a given type.
 * 
 * @author Silvan Wyss
 * @version 2017-03-05
 */
@SuppressWarnings("serial")
public final class UnrepresentingArgumentException extends InvalidArgumentException {

  private static final String DEFAULT_TYPE_NAME = Object.class.getSimpleName();

  private static final String A = "a";

  private static final String AN = "an";

  /**
   * Creates a new {@link UnrepresentingArgumentException} for the given argument
   * and type.
   * 
   * @param argument - Can be null.
   * @param type
   * @throws IllegalArgumentException if the given type is null.
   */
  private UnrepresentingArgumentException(final Object argument, final Class<?> type) {
    super(argument, new ErrorPredicateDto("does not represent " + getPronounAndNameOfType(type)));
  }

  /**
   * Creates a new {@link UnrepresentingArgumentException} for the given argument,
   * argumentName and type.
   * 
   * @param argument
   * @param argumentName
   * @param type
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   * @throws IllegalArgumentException if the given type is null.
   */
  private UnrepresentingArgumentException(final Object argument, final String argumentName, final Class<?> type) {
    super(argument, argumentName, "does not represent " + getPronounAndNameOfType(type));
  }

  /**
   * @param argument
   * @param argumentName
   * @param type
   * @return a new {@link UnrepresentingArgumentException} for the given argument,
   *         argumentName and type.
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   * @throws IllegalArgumentException if the given type is null.
   */
  public static UnrepresentingArgumentException forArgumentAndArgumentNameAndType(
    final Object argument,
    final String argumentName,
    final Class<?> type) {
    return new UnrepresentingArgumentException(argument, argumentName, type);
  }

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

  /**
   * @param type
   * @return the name of the given type.
   * @throws IllegalArgumentException if the given type is null.
   */
  private static String getNameOfType(final Class<?> type) {

    if (type == null) {
      throw new IllegalArgumentException("The given type is null.");
    }

    final var name = type.getSimpleName();

    if (name != null && !name.isEmpty()) {
      return name;
    }

    return DEFAULT_TYPE_NAME;
  }

  /**
   * @param type
   * @return the pronoun and name of the given type.
   * @throws IllegalArgumentException if the given type is null.
   */
  private static String getPronounAndNameOfType(final Class<?> type) {

    final var name = getNameOfType(type);
    final var pronoun = getPronounForNoun(name);

    return (pronoun + " " + name);
  }

  /**
   * @param noun
   * @return the pronoun for the given noun.
   * @throws IllegalArgumentException if the given noun is null or blank.
   */
  private static String getPronounForNoun(final String noun) {

    if (noun == null) {
      throw new IllegalArgumentException("The given noun is null.");
    }

    if (noun.isBlank()) {
      throw new IllegalArgumentException("The given noun is blank.");
    }

    //Enumerates the first character of the given noun.
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
        AN;
      default ->
        A;
    };
  }
}
