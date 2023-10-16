//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link InvalidArgumentException} is a {@link RuntimeException} that is
 * supposed to be thrown when a given argument is not valid.
 * 
 * A {@link InvalidArgumentException} stores the name of the argument the
 * {@link InvalidArgumentException} was created for. A
 * {@link InvalidArgumentException} stores the argument
 * {@link InvalidArgumentException} was created for.
 * 
 * The name of a {@link InvalidArgumentException} should be builded according to
 * one of the following patterns. -[A]ArgumentException
 * -Non[PA]ArgumentException -Argument[P]Exception Whereas: -[A] is an
 * adjective. -[PA] is a grammatically positive adjective. -[P] is a predicate.
 * 
 * Examples of names of {@link InvalidArgumentException}s:
 * -NegativeArgumentException -NonPositiveArgumentException
 * -ArgumentIsOutOfRangeException
 * 
 * @author Silvan Wyss
 * @date 2016-12-01
 */
@SuppressWarnings("serial")
public class InvalidArgumentException extends RuntimeException {

  //constant
  private static final int MAX_ARGUMENT_NAME_LENGTH = 100;

  //constant
  private static final String DEFAULT_ARGUMENT_NAME = "argument";

  //constant
  private static final String DEFAULT_ERROR_PREDICATE = "is not valid";

  //constant
  private static final char DOT = '.';

  //constant
  private static final char ELLIPSIS = 0x2026;

  //static method
  /**
   * @param argument
   * @return a new {@link InvalidArgumentException} for the given argument.
   */
  public static InvalidArgumentException forArgument(final Object argument) {
    return new InvalidArgumentException(argument);
  }

  //static method
  /**
   * @param argument
   * @param errorPredicate
   * @return a new {@link InvalidArgumentException} for the given argument and
   *         errorPredicate.
   * @throws IllegalArgumentException if the given errorPredicate is null.
   * @throws IllegalArgumentException if the given errorPredicate is blank.
   * @throws IllegalArgumentException if the given errorPredicate ends with a dot.
   */
  public static InvalidArgumentException forArgumentAndErrorPredicate(
      final Object argument,
      final String errorPredicate) {
    return new InvalidArgumentException(argument, errorPredicate);
  }

  //static method
  /**
   * @param argumentName
   * @param argument
   * @return a new {@link InvalidArgumentException} for the given argumentName and
   *         argument.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  public static InvalidArgumentException forArgumentNameAndArgument(
      final String argumentName,
      final Object argument) {
    return new InvalidArgumentException(argumentName, argument, DEFAULT_ERROR_PREDICATE);
  }

  //static method
  /**
   * @param argumentName
   * @param argument
   * @param errorPredicate
   * @return a new {@link InvalidArgumentException} for the given argumentName,
   *         argument and errorPredicate.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   * @throws IllegalArgumentException if the given errorPredicate is null.
   * @throws IllegalArgumentException if the given errorPredicate is blank.
   * @throws IllegalArgumentException if the given errorPredicate ends with a dot.
   */
  public static InvalidArgumentException forArgumentNameAndArgumentAndErrorPredicate(
      final String argumentName,
      final Object argument,
      final String errorPredicate) {
    return new InvalidArgumentException(argumentName, argument, errorPredicate);
  }

  //static method
  /**
   * @param argument
   * @return a argument name for the given argument.
   */
  private static String getNameOfArgument(final Object argument) {

    //Handles the case that the given argument is null.
    if (argument == null) {
      return DEFAULT_ARGUMENT_NAME;
    }

    //Handles the case that the given argument is not null.
    return argument.getClass().getSimpleName();
  }

  //static method
  /**
   * @param argument
   * @return a {@link String} representation of the given argument with puffer to
   *         next words in text.
   */
  private static String getStringRepresentationWithPufferToNextWordsOfArgument(final Object argument) {

    //Handles the case that the given argument is null.
    if (argument == null) {
      return " ";
    }

    //Gets the String representation of the given argument.
    final var string = argument.toString();

    //Handles the case that the String representation is null or blank.
    if (string == null || string.isBlank()) {
      return " ";
    }

    //Handles the case that the length of the String representation is not bigger
    //than the max argument name length.
    if (string.length() <= MAX_ARGUMENT_NAME_LENGTH) {
      return (" '" + string + "' ");
    }

    //Handles the case that the length of the String representation is bigger than
    //the max argument name length.
    return (" '" + string.substring(0, MAX_ARGUMENT_NAME_LENGTH) + ELLIPSIS + "' ");
  }

  //static method
  /**
   * @param argumentName
   * @return a valid argument name of the given argumentName.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  private static String getValidArgumentNameOfArgumentName(final String argumentName) {

    //Asserts that the given argumentName is not null.
    if (argumentName == null) {
      throw new IllegalArgumentException("The given argument name is null.");
    }

    //Asserts that the given argumentName is not blank.
    if (argumentName.isBlank()) {
      throw new IllegalArgumentException("The given argument name is blank.");
    }

    return argumentName;
  }

  //static method
  /**
   * @param errorPredicate
   * @return a valid error predicate of the given errorPredicate.
   * @throws IllegalArgumentException if the given errorPredicate is null.
   * @throws IllegalArgumentException if the given errorPredicate is blank.
   * @throws IllegalArgumentException if the given errorPredicate ends with a dot.
   */
  private static String getValidErrorPredicateOfErrorPredicate(final String errorPredicate) {

    //Asserts that the given errorPredicate is not null.
    if (errorPredicate == null) {
      throw new IllegalArgumentException("The given error predicate is null.");
    }

    //Asserts that the given errorPredicate is not blank.
    if (errorPredicate.isBlank()) {
      throw new IllegalArgumentException("The given error predicate is blank.");
    }

    //Asserts that the given errorPredicate does not end with a dot.
    if (errorPredicate.charAt(errorPredicate.length() - 1) == DOT) {
      throw new IllegalArgumentException("The given error predicate '" + errorPredicate + "' ends with a dot.");
    }

    return errorPredicate;
  }

  //attribute
  private final String argumentName;

  //attribute
  private final transient Object argument;

  //attribute
  private final String errorPredicate;

  //constructor
  /**
   * Creates a new {@link InvalidArgumentException} for the given argument.
   * 
   * @param argument
   */
  protected InvalidArgumentException(final Object argument) {

    //Calls other constructor.
    this(getNameOfArgument(argument), argument, DEFAULT_ERROR_PREDICATE);
  }

  //constructor
  /**
   * Creates a new {@link InvalidArgumentException} for the given argument and
   * errorPredicate.
   * 
   * @param argument
   * @param errorPredicate
   * @throws IllegalArgumentException if the given errorPredicate is null.
   * @throws IllegalArgumentException if the given errorPredicate is blank.
   * @throws IllegalArgumentException if the given errorPredicate ends with a dot.
   */
  protected InvalidArgumentException(final Object argument, final String errorPredicate) {

    //Calls other constructor.
    this(getNameOfArgument(argument), argument, errorPredicate);
  }

  //constructor
  /**
   * Creates a new {@link InvalidArgumentException} for the given argumentName,
   * argument and errorPredicate.
   * 
   * @param argumentName
   * @param argument
   * @param errorPredicate
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   * @throws IllegalArgumentException if the given errorPredicate is null.
   * @throws IllegalArgumentException if the given errorPredicate is blank.
   * @throws IllegalArgumentException if the given errorPredicate ends with a dot.
   */
  protected InvalidArgumentException(final String argumentName, final Object argument, final String errorPredicate) {

    super(
        "The given "
            + getValidArgumentNameOfArgumentName(argumentName)
            + getStringRepresentationWithPufferToNextWordsOfArgument(argument)
            + getValidErrorPredicateOfErrorPredicate(errorPredicate)
            + ".");

    this.argumentName = argumentName;
    this.argument = argument;
    this.errorPredicate = errorPredicate;
  }

  //method
  /**
   * @return the name of the argument of the current
   *         {@link InvalidArgumentException}.
   */
  public final String getArgumentName() {
    return argumentName;
  }

  //method
  /**
   * @return the error predicate of the current {@link InvalidArgumentException}.
   */
  public final String getErrorPredicate() {
    return errorPredicate;
  }

  //method
  /**
   * @return the argument of the current {@link InvalidArgumentException}.
   */
  public final Object getStoredArgument() {
    return argument;
  }
}
