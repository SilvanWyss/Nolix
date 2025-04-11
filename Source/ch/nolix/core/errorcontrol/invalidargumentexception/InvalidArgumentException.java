package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.core.errorcontrol.exceptionargumentpreparator.ExceptionArgumentNamePreparator;
import ch.nolix.core.errorcontrol.exceptionargumentpreparator.ExceptionArgumentStringRepresentaionPreparator;
import ch.nolix.core.errorcontrol.exceptionargumentpreparator.ExceptionCausePreparator;
import ch.nolix.core.errorcontrol.exceptionargumentpreparator.ExceptionErrorPredicatePreparator;
import ch.nolix.coreapi.errorcontrolapi.exceptionargumentboxapi.ArgumentNameDto;
import ch.nolix.coreapi.errorcontrolapi.exceptionargumentboxapi.ErrorPredicateDto;
import ch.nolix.coreapi.errorcontrolapi.exceptionargumentpreparatorapi.IExceptionArgumentNamePreparator;
import ch.nolix.coreapi.errorcontrolapi.exceptionargumentpreparatorapi.IExceptionArgumentStringRepresentaionPreparator;
import ch.nolix.coreapi.errorcontrolapi.exceptionargumentpreparatorapi.IExceptionCausePreparator;
import ch.nolix.coreapi.errorcontrolapi.exceptionargumentpreparatorapi.IExceptionErrorPredicatePreparator;

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
 * Let [A] be an adjective, [PA] a grammatically positive adjective and [P] a
 * predicate. The name of a {@link InvalidArgumentException} class should match
 * one of the following patterns: [A]ArgumentException, Non[PA]ArgumentExceptio,
 * Argument[P]Exception.
 * 
 * Examples of {@link InvalidArgumentException} names:
 * NegativeArgumentException, NonPositiveArgumentException,
 * ArgumentIsOutOfRangeException
 * 
 * @author Silvan Wyss
 * @version 2016-12-01
 */
@SuppressWarnings("serial")
public class InvalidArgumentException extends RuntimeException {

  private static final String DEFAULT_ARGUMENT_NAME = "argument";

  private static final String DEFAULT_ERROR_PREDICATE = "is not valid";

  private static final IExceptionArgumentStringRepresentaionPreparator ARGUMENT_STRING_REPRESENTAION_PREPARATOR = //
  new ExceptionArgumentStringRepresentaionPreparator();

  private static final IExceptionArgumentNamePreparator ARGUMENT_NAME_PREPARATOR = new ExceptionArgumentNamePreparator();

  private static final IExceptionErrorPredicatePreparator ERROR_PREDICATE_PREPARATOR = new ExceptionErrorPredicatePreparator();

  private static final IExceptionCausePreparator CAUSE_PREPARATOR = new ExceptionCausePreparator();

  private final transient Object argument;

  private final String argumentName;

  private final String errorPredicate;

  /**
   * Creates a new {@link InvalidArgumentException} for the given argument.
   * 
   * @param argument
   */
  protected InvalidArgumentException(final Object argument) {
    this(argument, ARGUMENT_NAME_PREPARATOR.getNameOfArgument(argument), DEFAULT_ERROR_PREDICATE);
  }

  /**
   * Creates a new {@link InvalidArgumentException} for the given argument and the
   * argumentName of the given argumentNameDto.
   * 
   * @param argument
   * @param argumentNameDto
   * @throws NullPointerException     if the given argumentNameDto is null.
   * @throws IllegalArgumentException if the argumentName of the given
   *                                  argumentNameDto is null or blank.
   */
  protected InvalidArgumentException(final Object argument, final ArgumentNameDto argumentNameDto) {
    this(
      argument,
      ARGUMENT_NAME_PREPARATOR.getValidatedArgumentNameFromArgumentName(argumentNameDto.argumentName()),
      DEFAULT_ERROR_PREDICATE);
  }

  /**
   * Creates a new {@link InvalidArgumentException} for the given argument and the
   * errorPredicate of the given errorPredicateDto.
   * 
   * @param argument          - Can be null.
   * @param errorPredicateDto
   * @throws NullPointerException     if the given errorPredicateDto is null.
   * @throws IllegalArgumentException if the errorPredicate of the given
   *                                  errorPredicateDto is null or blank.
   */
  protected InvalidArgumentException(final Object argument, final ErrorPredicateDto errorPredicateDto) {
    this(
      argument,
      ARGUMENT_NAME_PREPARATOR.getNameOfArgument(argument),
      ERROR_PREDICATE_PREPARATOR.getValidErrorPredicateFromErrorPredicate(errorPredicateDto.errorPredicate()));
  }

  /**
   * Creates a new {@link InvalidArgumentException} for the given argument,
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
    this(
      ARGUMENT_NAME_PREPARATOR.getValidatedArgumentNameFromArgumentName(argumentName),
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
      + ARGUMENT_NAME_PREPARATOR.getValidatedArgumentNameFromArgumentName(argumentName)
      + ARGUMENT_STRING_REPRESENTAION_PREPARATOR
        .getSmartSpaceEnclosedQuotedStringRepresentationWithMaxLengthOfArgument(argument)
      + ERROR_PREDICATE_PREPARATOR.getValidErrorPredicateFromErrorPredicate(errorPredicate)
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
      + ARGUMENT_NAME_PREPARATOR.getValidatedArgumentNameFromArgumentName(argumentName)
      + ARGUMENT_STRING_REPRESENTAION_PREPARATOR
        .getSmartSpaceEnclosedQuotedStringRepresentationWithMaxLengthOfArgument(argument)
      + ERROR_PREDICATE_PREPARATOR.getValidErrorPredicateFromErrorPredicate(errorPredicate)
      + ".",
      CAUSE_PREPARATOR.getValidatedCauseFromCause(cause));

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
   * @param argument       - Can be null.
   * @param errorPredicate
   * @return a new {@link InvalidArgumentException} for the given argument and
   *         errorPredicate.
   * @throws IllegalArgumentException if the given errorPredicate is null or blank
   *                                  or ends with a dot.
   */
  public static InvalidArgumentException forArgumentAndErrorPredicate(
    final Object argument,
    final String errorPredicate) {
    return new InvalidArgumentException(argument, new ErrorPredicateDto(errorPredicate));
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
