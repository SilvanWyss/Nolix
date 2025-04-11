package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrolapi.exceptionargumentboxapi.ArgumentNameDto;
import ch.nolix.coreapi.errorcontrolapi.exceptionargumentboxapi.CauseDto;
import ch.nolix.coreapi.errorcontrolapi.exceptionargumentboxapi.ErrorPredicateDto;

/**
 * @author Silvan Wyss
 * @version 2016-12-01
 */
@SuppressWarnings("serial")
public final class InvalidArgumentException extends AbstractInvalidArgumentException {

  /**
   * Creates a new {@link InvalidArgumentException} for the given argument.
   * 
   * @param argument - Can be null.
   */
  protected InvalidArgumentException(final Object argument) {
    super(argument);
  }

  /**
   * Creates a new {@link InvalidArgumentException} for the given argument and the
   * argumentName of the given argumentNameDto.
   * 
   * @param argument        - Can be null.
   * @param argumentNameDto
   * @throws RuntimeException if the given argumentNameDto is null.
   * @throws RuntimeException if the argumentName of the given argumentNameDto is
   *                          null or blank.
   */
  protected InvalidArgumentException(final Object argument, final ArgumentNameDto argumentNameDto) {
    super(argument, argumentNameDto);
  }

  /**
   * Creates a new {@link InvalidArgumentException} for the given argument and the
   * errorPredicate of the given errorPredicateDto.
   * 
   * @param argument          - Can be null.
   * @param errorPredicateDto
   * @throws RuntimeException if the given errorPredicateDto is null.
   * @throws RuntimeException if the errorPredicate of the given errorPredicateDto
   *                          is null or blank.
   */
  protected InvalidArgumentException(final Object argument, final ErrorPredicateDto errorPredicateDto) {
    super(argument, errorPredicateDto);
  }

  /**
   * Creates a new {@link InvalidArgumentException} for the given argument,
   * argumentName and errorPredicate.
   * 
   * @param argument
   * @param argumentNameDto
   * @param errorPredicateDto
   * @throws RuntimeException if the given argumentNameDto is null.
   * @throws RuntimeException if the argumentName of the given argumentNameDto is
   *                          null or blank.
   * @throws RuntimeException if the given errorPredicateDto is null.
   * @throws RuntimeException if the errorPredicate of the given errorPredicateDto
   *                          is null or blank.
   */
  protected InvalidArgumentException(final Object argument, final ArgumentNameDto argumentNameDto,
    final ErrorPredicateDto errorPredicateDto) {
    super(argument, argumentNameDto, errorPredicateDto);
  }

  /**
   * Creates a new {@link InvalidArgumentException} for the given argument,
   * argumentName of the given argumentNameDto, errorPredicate of the given
   * errorPredicateDto and cause of the given causeDto.
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
  protected InvalidArgumentException(
    final Object argument,
    final ArgumentNameDto argumentNameDto,
    final ErrorPredicateDto errorPredicateDto,
    final CauseDto causeDto) {
    super(argument, argumentNameDto, errorPredicateDto, causeDto);
  }

  /**
   * @param argument - Can be null.
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
   * @throws RuntimeException if the given errorPredicate is null or blank or ends
   *                          with a dot.
   */
  public static InvalidArgumentException forArgumentAndErrorPredicate(
    final Object argument,
    final String errorPredicate) {
    return new InvalidArgumentException(argument, new ErrorPredicateDto(errorPredicate));
  }

  /**
   * @param argument     - Can be null.
   * @param argumentName
   * @return a new {@link InvalidArgumentException} for the given argument and
   *         argumentName.
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  public static InvalidArgumentException forArgumentAndArgumentName(final Object argument, final String argumentName) {
    return new InvalidArgumentException(argument, new ArgumentNameDto(argumentName));
  }

  /**
   * @param argument
   * @param argumentName
   * @param errorPredicate
   * @return a new {@link InvalidArgumentException} for the given argument,
   *         argumentName and errorPredicate.
   * @throws RuntimeException if the given argumentName is null or blank.
   * @throws RuntimeException if the given errorPredicate is null or blank or ends
   *                          with a dot.
   */
  public static InvalidArgumentException forArgumentAndArgumentNameAndErrorPredicate(
    final Object argument,
    final String argumentName,
    final String errorPredicate) {
    return //
    new InvalidArgumentException(argument, new ArgumentNameDto(argumentName), new ErrorPredicateDto(errorPredicate));
  }
}
