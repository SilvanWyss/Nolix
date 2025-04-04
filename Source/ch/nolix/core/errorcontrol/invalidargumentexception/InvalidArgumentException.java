package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.core.errorcontrol.exceptionargumentpreparator.ExceptionArgumentNamePreparator;
import ch.nolix.coreapi.errorcontrolapi.exceptionargumentpreparatorapi.IExceptionArgumentNamePreparator;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.CharacterCatalog;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.StringCatalog;

/**
 * A {@link InvalidArgumentException} is a {@link RuntimeException} that is
 * supposed to be thrown when a given argument is not valid.
 * 
 * A {@link InvalidArgumentException} stores the argument the
 * {@link InvalidArgumentException} was created for.
 * 
 * A {@link InvalidArgumentException} stores the name of the argument the
 * {@link InvalidArgumentException} was created for.
 * 
 * The name of a {@link InvalidArgumentException} class should be builded
 * according to one of the following patterns: [A]ArgumentException,
 * Non[PA]ArgumentExceptio, Argument[P]Exception. Whereas: [A] is an adjective,
 * [PA] is a grammatically positive adjective, [P] is a predicate.
 * 
 * Examples of names of {@link InvalidArgumentException} classes:
 * NegativeArgumentException, NonPositiveArgumentException,
 * ArgumentIsOutOfRangeException
 * 
 * @author Silvan Wyss
 * @version 2016-12-01
 */
@SuppressWarnings("serial")
public class InvalidArgumentException extends RuntimeException {

  protected static final String DEFAULT_ARGUMENT_NAME = "argument";

  private static final int MAX_ARGUMENT_STRING_REPRESENTATION_LENGTH = 200;

  private static final String ANONYMOUS_CLASS_INSTANCE_ARGUMENT_NAME = "instance of an anonymous class";

  private static final String DEFAULT_ERROR_PREDICATE = "is not valid";

  private static final IExceptionArgumentNamePreparator EXCEPTION_ARGUMENT_NAME_PREPARATOR = new ExceptionArgumentNamePreparator();

  private final transient Object argument;

  private final String argumentName;

  private final String errorPredicate;

  /**
   * Creates a new {@link InvalidArgumentException} for the given argument.
   * 
   * @param argument
   */
  protected InvalidArgumentException(final Object argument) {
    this(argument, getNameOfArgument(argument), DEFAULT_ERROR_PREDICATE);
  }

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

  /**
   * Creates a new {@link InvalidArgumentException} for the given argument and
   * argumentName and errorPredicate.
   * 
   * @param argument
   * @param argumentName
   * @param errorPredicate
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   * @throws IllegalArgumentException if the given errorPredicate is null or blank
   *                                  or ends with a dot.
   */
  protected InvalidArgumentException(final Object argument, final String argumentName, final String errorPredicate) {

    //Calls other constructor.
    this(EXCEPTION_ARGUMENT_NAME_PREPARATOR.getValidatedArgumentNameOfArgumentName(argumentName),
      argument,
      errorPredicate);
  }

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
      + EXCEPTION_ARGUMENT_NAME_PREPARATOR.getValidatedArgumentNameOfArgumentName(argumentName)
      + getValidStringRepresentationWithPufferToNextWordsOfArgument(argument)
      + getValidErrorPredicateOfErrorPredicate(errorPredicate)
      + ".");

    this.argumentName = argumentName;
    this.argument = argument;
    this.errorPredicate = errorPredicate;
  }

  /**
   * Creates a new {@link InvalidArgumentException} for the given argumentName,
   * argument, errorPredicate and cause.
   * 
   * @param argumentName
   * @param argument
   * @param errorPredicate
   * @param cause
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   * @throws IllegalArgumentException if the given errorPredicate is null.
   * @throws IllegalArgumentException if the given errorPredicate is blank.
   * @throws IllegalArgumentException if the given errorPredicate ends with a dot.
   * @throws IllegalArgumentException if the given cause is null.
   */
  protected InvalidArgumentException(
    final String argumentName,
    final Object argument,
    final String errorPredicate,
    final Throwable cause) {

    super(
      "The given "
      + EXCEPTION_ARGUMENT_NAME_PREPARATOR.getValidatedArgumentNameOfArgumentName(argumentName)
      + getValidStringRepresentationWithPufferToNextWordsOfArgument(argument)
      + getValidErrorPredicateOfErrorPredicate(errorPredicate)
      + ".",
      getValidCauseOfCause(cause));

    this.argumentName = argumentName;
    this.argument = argument;
    this.errorPredicate = errorPredicate;
  }

  /**
   * @param argument
   * @return a new {@link InvalidArgumentException} for the given argument.
   */
  public static InvalidArgumentException forArgument(final Object argument) {
    return new InvalidArgumentException(argument);
  }

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

  /**
   * @param argument
   * @param errorPredicate
   * @param cause
   * @return a new {@link InvalidArgumentException} for the given argument and
   *         errorPredicate.
   * @throws IllegalArgumentException if the given errorPredicate is null.
   * @throws IllegalArgumentException if the given errorPredicate is blank.
   * @throws IllegalArgumentException if the given errorPredicate ends with a dot.
   * @throws IllegalArgumentException if the given cause is null.
   */
  public static InvalidArgumentException forArgumentAndErrorPredicateAndCause(
    final Object argument,
    final String errorPredicate,
    final Throwable cause) {
    return new InvalidArgumentException(DEFAULT_ARGUMENT_NAME, argument, errorPredicate, cause);
  }

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
    return getNameOfArgumentThatIsInstanceOfClass(argument.getClass());
  }

  /**
   * @param anonymousClass
   * @return a name of an argument that is an instance of the given paramClass.
   * @throws IllegalArgumentException if the given anonymousClass is null.
   */
  private static String getNameOfArgumentThatIsInstanceOfAnonymousClass(final Class<?> anonymousClass) {

    //Handles the case that the given anonymousClass is null.
    if (anonymousClass == null) {
      throw new IllegalArgumentException("The given class is null.");
    }

    //Gets the super class of the given anonymousClass.
    final var superClass = anonymousClass.getSuperclass();

    //Handles the case that the super class is null.
    if (superClass == null) {
      return ANONYMOUS_CLASS_INSTANCE_ARGUMENT_NAME;
    }

    //Handles the case that the super class is not null.
    return getNameOfArgumentThatIsInstanceOfClass(superClass);
  }

  /**
   * @param paramClass
   * @return a name of an argument that is an instance of the given paramClass.
   * @throws IllegalArgumentException if the given paramClass is null.
   */
  private static String getNameOfArgumentThatIsInstanceOfClass(final Class<?> paramClass) {

    //Handles the case that the given paramClass is null.
    if (paramClass == null) {
      throw new IllegalArgumentException("The given class is null.");
    }

    //Handles the case that the given paramClass is not anonymous.
    if (!paramClass.isAnonymousClass()) {
      return paramClass.getSimpleName();
    }

    //Handles the case that the given paramClass is anonymous.
    return getNameOfArgumentThatIsInstanceOfAnonymousClass(paramClass);
  }

  /**
   * @param cause
   * @return a valid cause of the given cause
   * @throws IllegalArgumentException if the given cause is null.
   * 
   */
  private static Throwable getValidCauseOfCause(final Throwable cause) {

    //Asserts that the given cause is not null.
    if (cause == null) {
      throw new IllegalArgumentException("The given cause is null.");
    }

    return cause;
  }

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
    if (errorPredicate.charAt(errorPredicate.length() - 1) == CharacterCatalog.DOT) {
      throw new IllegalArgumentException("The given error predicate '" + errorPredicate + "' ends with a dot.");
    }

    return errorPredicate;
  }

  /**
   * @param argument
   * @return a valid {@link String} representation of the given argument.
   */
  private static String getValidStringRepresentationOfArgument(final Object argument) {

    //Handles the case that the given argument is null.
    if (argument == null) {
      return StringCatalog.EMPTY_STRING;
    }

    try {

      //Gets the String representation of the given argument.
      final var string = argument.toString();

      //Handles the case that the String representation is null.
      if (string == null) {
        return StringCatalog.NULL_HEADER;
      }

      //Handles the case that the String representation is not null.
      return string;
    } catch (final Throwable error) { //NOSONAR: Each Throwable must be caught here.
      return StringCatalog.EMPTY_STRING;
    }
  }

  /**
   * @param argument
   * @return a valid {@link String} representation of the given argument with
   *         puffer to next words in text.
   */
  private static String getValidStringRepresentationWithPufferToNextWordsOfArgument(final Object argument) {

    //Gets the String Representation of the given argument.
    final var stringRepresentation = getValidStringRepresentationOfArgument(argument);

    //Handles the case that the stringRepresentation is blank.
    if (stringRepresentation.isBlank()) {
      return StringCatalog.SPACE;
    }

    //Handles the case that the length of the stringRepresentation is not bigger
    //than the max argument name length.
    if (stringRepresentation.length() <= MAX_ARGUMENT_STRING_REPRESENTATION_LENGTH) {
      return (" '" + stringRepresentation + "' ");
    }

    //Handles the case that the length of the stringRepresentation is bigger than
    //the max argument name length.
    return (" '" + stringRepresentation.substring(0, MAX_ARGUMENT_STRING_REPRESENTATION_LENGTH)
    + CharacterCatalog.ELLIPSIS + "' ");
  }

  /**
   * @return the name of the argument of the current
   *         {@link InvalidArgumentException}.
   */
  public final String getArgumentName() {
    return argumentName;
  }

  /**
   * @return the error predicate of the current {@link InvalidArgumentException}.
   */
  public final String getErrorPredicate() {
    return errorPredicate;
  }

  /**
   * @return the argument of the current {@link InvalidArgumentException}.
   */
  public final Object getStoredArgument() {
    return argument;
  }
}
