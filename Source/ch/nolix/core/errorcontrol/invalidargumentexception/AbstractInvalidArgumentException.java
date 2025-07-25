package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.core.errorcontrol.exceptionargumentpreparator.ExceptionArgumentNamePreparator;
import ch.nolix.core.errorcontrol.exceptionargumentpreparator.ExceptionArgumentStringRepresentaionPreparator;
import ch.nolix.core.errorcontrol.exceptionargumentpreparator.ExceptionCausePreparator;
import ch.nolix.core.errorcontrol.exceptionargumentpreparator.ExceptionErrorPredicatePreparator;
import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ArgumentNameDto;
import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.CauseDto;
import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ErrorPredicateDto;
import ch.nolix.coreapi.errorcontrol.exceptionargumentpreparator.IExceptionArgumentNamePreparator;
import ch.nolix.coreapi.errorcontrol.exceptionargumentpreparator.IExceptionArgumentStringRepresentaionPreparator;
import ch.nolix.coreapi.errorcontrol.exceptionargumentpreparator.IExceptionCausePreparator;
import ch.nolix.coreapi.errorcontrol.exceptionargumentpreparator.IExceptionErrorPredicatePreparator;

/**
 * A {@link AbstractInvalidArgumentException} is a {@link RuntimeException} that
 * is supposed to be thrown when a given argument is not valid.
 * 
 * A {@link AbstractInvalidArgumentException} stores the argument the
 * {@link AbstractInvalidArgumentException} was created for.
 * 
 * A {@link AbstractInvalidArgumentException} stores the name of the argument
 * the {@link AbstractInvalidArgumentException} was created for.
 * 
 * @author Silvan Wyss
 * @version 2025-04-11
 */
@SuppressWarnings("serial")
public abstract class AbstractInvalidArgumentException extends RuntimeException {

  private static final String DEFAULT_ERROR_PREDICATE = "is not valid";

  private static final IExceptionArgumentStringRepresentaionPreparator ARGUMENT_STRING_REPRESENTAION_PREPARATOR = //
  new ExceptionArgumentStringRepresentaionPreparator();

  private static final IExceptionArgumentNamePreparator ARGUMENT_NAME_PREPARATOR = //
  new ExceptionArgumentNamePreparator();

  private static final IExceptionErrorPredicatePreparator ERROR_PREDICATE_PREPARATOR = //
  new ExceptionErrorPredicatePreparator();

  private static final IExceptionCausePreparator CAUSE_PREPARATOR = new ExceptionCausePreparator();

  private final transient Object argument;

  private final String argumentName;

  private final String errorPredicate;

  /**
   * Creates a new {@link AbstractInvalidArgumentException} for the given
   * argument.
   * 
   * @param argument - Can be null.
   */
  protected AbstractInvalidArgumentException(final Object argument) {

    super(
      "The given "
      + ARGUMENT_NAME_PREPARATOR.getNameOfArgument(argument)
      + ARGUMENT_STRING_REPRESENTAION_PREPARATOR
        .getSmartSpaceEnclosedQuotedStringRepresentationWithMaxLengthOfArgument(argument)
      + DEFAULT_ERROR_PREDICATE
      + ".");

    this.argument = argument;
    this.argumentName = ARGUMENT_NAME_PREPARATOR.getNameOfArgument(argument);
    this.errorPredicate = DEFAULT_ERROR_PREDICATE;
  }

  /**
   * Creates a new {@link AbstractInvalidArgumentException} for the given argument
   * and argumentName of the given argumentNameDto
   * 
   * @param argument        - Can be null.
   * @param argumentNameDto
   * @throws RuntimeException if the given argumentNameDto is null.
   * @throws RuntimeException if the argumentName of the given argumentNameDto is
   *                          null or blank.
   */
  protected AbstractInvalidArgumentException(final Object argument, final ArgumentNameDto argumentNameDto) {

    super(
      "The given "
      + ARGUMENT_NAME_PREPARATOR.getValidatedArgumentNameFromArgumentName(argumentNameDto.argumentName())
      + ARGUMENT_STRING_REPRESENTAION_PREPARATOR
        .getSmartSpaceEnclosedQuotedStringRepresentationWithMaxLengthOfArgument(argument)
      + DEFAULT_ERROR_PREDICATE
      + ".");

    this.argument = argument;
    this.argumentName = argumentNameDto.argumentName();
    this.errorPredicate = DEFAULT_ERROR_PREDICATE;
  }

  /**
   * Creates a new {@link AbstractInvalidArgumentException} for the given
   * argument, argumentName of the given argumentNameDto and errorPredicate of the
   * given errorPredicateDto.
   * 
   * @param argument          - Can be null.
   * @param argumentNameDto
   * @param errorPredicateDto
   * @throws RuntimeException if the given argumentNameDto is null.
   * @throws RuntimeException if the argumentName of the given argumentNameDto is
   *                          null or blank.
   * @throws RuntimeException if the given errorPredicateDto is null.
   * @throws RuntimeException if the errorPredicate of the given errorPredicateDto
   *                          is null or blank.
   */
  protected AbstractInvalidArgumentException(
    final Object argument,
    final ArgumentNameDto argumentNameDto,
    final ErrorPredicateDto errorPredicateDto) {

    super(
      "The given "
      + ARGUMENT_NAME_PREPARATOR.getValidatedArgumentNameFromArgumentName(argumentNameDto.argumentName())
      + ARGUMENT_STRING_REPRESENTAION_PREPARATOR
        .getSmartSpaceEnclosedQuotedStringRepresentationWithMaxLengthOfArgument(argument)
      + ERROR_PREDICATE_PREPARATOR.getValidErrorPredicateFromErrorPredicate(errorPredicateDto.errorPredicate())
      + ".");

    this.argument = argument;
    this.argumentName = argumentNameDto.argumentName();
    this.errorPredicate = errorPredicateDto.errorPredicate();
  }

  /**
   * Creates a new {@link AbstractInvalidArgumentException} for the given
   * argument, argumentName of the given argumentNameDto, errorPredicate of the
   * given errorPredicateDto and cause of the given causeDto.
   * 
   * @param argument          - Can be null.
   * @param argumentNameDto
   * @param errorPredicateDto
   * @param causeDto
   * @throws RuntimeException if the given argumentNameDto is null.
   * @throws RuntimeException if the argumentName of the given argumentNameDto is
   *                          null or blank.
   * @throws RuntimeException if the given errorPredicateDto is null.
   * @throws RuntimeException if the errorPredicate of the given errorPredicateDto
   *                          is null or blank.
   * @throws RuntimeException if the given causeDto is null.
   * @throws RuntimeException if the cause of the given causeDto is null.
   */
  protected AbstractInvalidArgumentException(
    final Object argument,
    final ArgumentNameDto argumentNameDto,
    final ErrorPredicateDto errorPredicateDto,
    final CauseDto causeDto) {

    super(
      "The given "
      + ARGUMENT_NAME_PREPARATOR.getValidatedArgumentNameFromArgumentName(argumentNameDto.argumentName())
      + ARGUMENT_STRING_REPRESENTAION_PREPARATOR
        .getSmartSpaceEnclosedQuotedStringRepresentationWithMaxLengthOfArgument(argument)
      + ERROR_PREDICATE_PREPARATOR.getValidErrorPredicateFromErrorPredicate(errorPredicateDto.errorPredicate())
      + ".",
      CAUSE_PREPARATOR.getValidatedCauseFromCause(causeDto.cause()));

    this.argument = argument;
    this.argumentName = argumentNameDto.argumentName();
    this.errorPredicate = errorPredicateDto.errorPredicate();
  }

  /**
   * Creates a new {@link AbstractInvalidArgumentException} for the given argument
   * and the errorPredicate of the given errorPredicateDto.
   * 
   * @param argument          - Can be null.
   * @param errorPredicateDto
   * @throws RuntimeException if the given errorPredicateDto is null.
   * @throws RuntimeException if the errorPredicate of the given errorPredicateDto
   *                          is null or blank.
   */
  protected AbstractInvalidArgumentException(final Object argument, final ErrorPredicateDto errorPredicateDto) {

    super(
      "The given "
      + ARGUMENT_NAME_PREPARATOR.getNameOfArgument(argument)
      + ARGUMENT_STRING_REPRESENTAION_PREPARATOR
        .getSmartSpaceEnclosedQuotedStringRepresentationWithMaxLengthOfArgument(argument)
      + ERROR_PREDICATE_PREPARATOR.getValidErrorPredicateFromErrorPredicate(errorPredicateDto.errorPredicate())
      + ".");

    this.argument = argument;
    this.argumentName = ARGUMENT_NAME_PREPARATOR.getNameOfArgument(argument);
    this.errorPredicate = errorPredicateDto.errorPredicate();
  }

  /**
   * @return the name of the argument of the current
   *         {@link AbstractInvalidArgumentException}.
   */
  public final String getArgumentName() {
    return argumentName;
  }

  /**
   * @return the error predicate of the current
   *         {@link AbstractInvalidArgumentException}.
   */
  public final String getErrorPredicate() {
    return errorPredicate;
  }

  /**
   * @return the argument of the current {@link AbstractInvalidArgumentException}.
   */
  public final Object getStoredArgument() {
    return argument;
  }
}
