package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ArgumentNameDto;
import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ErrorPredicateDto;

/**
 * A {@link ArgumentDoesNotContainElementException} is a
 * {@link AbstractInvalidArgumentException} that is supposed to be thrown when a
 * given argument does undesirably not contain a given element.
 * 
 * @author Silvan Wyss
 * @version 2021-07-15
 */
@SuppressWarnings("serial")
public final class ArgumentDoesNotContainElementException extends AbstractInvalidArgumentException {
  private static final String DEFAULT_ELEMENT_NAME = "element";

  /**
   * Creates a new {@link ArgumentDoesNotContainElementException} for the given
   * argument.
   * 
   * @param argument - Can be null.
   */
  private ArgumentDoesNotContainElementException(final Object argument) {
    super(argument, new ErrorPredicateDto("does not contain such an element"));
  }

  /**
   * Creates a new {@link ArgumentDoesNotContainElementException} for the given
   * argument and element.
   * 
   * @param argument - Can be null.
   * @param element  - Can be null.
   */
  private ArgumentDoesNotContainElementException(final Object argument, final Object element) {
    super(argument, new ErrorPredicateDto("does not contain the given " + getNameOfElement(element)));
  }

  /**
   * Creates a new {@link ArgumentDoesNotContainElementException} for the given
   * argument, argumentName and element.
   * 
   * @param argument     - Can be null.
   * @param argumentName
   * @param element      - Can be null.
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  private ArgumentDoesNotContainElementException(
    final Object argument,
    final String argumentName,
    final Object element) {
    super(
      argument,
      new ArgumentNameDto(argumentName),
      new ErrorPredicateDto("does not contain the given " + getNameOfElement(element)));
  }

  /**
   * @param argument - Can be null.
   * @return a new {@link ArgumentDoesNotContainElementException} for the given
   *         argument.
   */
  public static ArgumentDoesNotContainElementException forArgument(final Object argument) {
    return new ArgumentDoesNotContainElementException(argument);
  }

  /**
   * @param argument     - Can be null.
   * @param argumentName
   * @param element      - Can be null.
   * @return a new {@link ArgumentDoesNotContainElementException} for the given
   *         argument, argumentName and element.
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  public static ArgumentDoesNotContainElementException forArgumentAndArgumentNameAndElement(
    final Object argument,
    final String argumentName,
    final Object element) {
    return new ArgumentDoesNotContainElementException(argument, argumentName, element);
  }

  /**
   * @param argument - Can be null.
   * @param element  - Can be null.
   * @return a new {@link ArgumentDoesNotContainElementException} for the given
   *         argument and element.
   */
  public static ArgumentDoesNotContainElementException forArgumentAndElement(
    final Object argument,
    final Object element) {
    return new ArgumentDoesNotContainElementException(argument, element);
  }

  /**
   * @param element - Can be null.
   * @return a name of the given element.
   */
  private static String getNameOfElement(final Object element) {
    if (element != null) {
      return getNameOfElementWhenIsNotNull(element);
    }

    return DEFAULT_ELEMENT_NAME;
  }

  /**
   * @param element
   * @return a name of the given element for the case that the given element is
   *         not null.
   */
  private static String getNameOfElementWhenIsNotNull(final Object element) {
    final var name = element.getClass().getSimpleName();

    if (name != null && !name.isEmpty()) {
      return name;
    }

    return DEFAULT_ELEMENT_NAME;
  }
}
