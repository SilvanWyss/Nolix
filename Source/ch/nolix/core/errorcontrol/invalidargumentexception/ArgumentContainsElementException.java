package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrolapi.exceptionargumentboxapi.ErrorPredicateDto;

/**
 * A {@link ArgumentContainsElementException} is a
 * {@link AbstractInvalidArgumentException} that is supposed to be thrown when a
 * given argument contains undesirably a given element.
 * 
 * @author Silvan Wyss
 * @version 2021-07-15
 */
@SuppressWarnings("serial")
public final class ArgumentContainsElementException extends AbstractInvalidArgumentException {

  private static final String DEFAULT_ELEMENT_NAME = "element";

  /**
   * Creates a new {@link ArgumentContainsElementException} for the given argument
   * and element.
   * 
   * @param argument - Can be null.
   * @param element  - Can be null.
   */
  private ArgumentContainsElementException(final Object argument, final Object element) {
    super(argument, new ErrorPredicateDto("contains already the given " + getNameOfElement(element)));
  }

  /**
   * @param argument - Can be null.
   * @param element  - Can be null.
   * @return a new {@link ArgumentContainsElementException} for the given argument
   *         and element.
   */
  public static ArgumentContainsElementException forArgumentAndElement(final Object argument, final Object element) {
    return new ArgumentContainsElementException(argument, element);
  }

  /**
   * @param element - Can be null.
   * @return the name of the given element.
   */
  private static String getNameOfElement(final Object element) {

    if (element != null) {
      return getNameOfElementWhenIsNotNull(element);
    }

    return DEFAULT_ELEMENT_NAME;
  }

  /**
   * @param element
   * @return the name of the given element for the case that the given element is
   *         not null.
   * @throws RuntimeException if the given element is null.
   */
  private static String getNameOfElementWhenIsNotNull(final Object element) {

    final var name = element.getClass().getSimpleName();

    if (name != null && !name.isEmpty()) {
      return name;
    }

    return DEFAULT_ELEMENT_NAME;
  }
}
