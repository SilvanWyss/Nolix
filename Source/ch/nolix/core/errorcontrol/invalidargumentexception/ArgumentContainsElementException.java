package ch.nolix.core.errorcontrol.invalidargumentexception;

/**
 * A {@link ArgumentContainsElementException} is a
 * {@link InvalidArgumentException} that is supposed to be thrown when a given
 * argument contains undesirably a given element.
 * 
 * @author Silvan Wyss
 * @version 2021-07-15
 */
@SuppressWarnings("serial")
public final class ArgumentContainsElementException extends InvalidArgumentException {

  private static final String DEFAULT_ELEMENT_NAME = "element";

  /**
   * Creates a new {@link ArgumentContainsElementException} for the given
   * argument, argumentName and element.
   * 
   * @param argument
   * @param argumentName
   * @param element
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  private ArgumentContainsElementException(final Object argument, final String argumentName, final Object element) {
    super(argument, argumentName, "contains already the given " + getNameOfElement(element));
  }

  /**
   * @param argument
   * @param element
   * @return a new {@link ArgumentContainsElementException} for the given argument
   *         and element.
   */
  public static ArgumentContainsElementException forArgumentAndElement(final Object argument, final Object element) {
    return new ArgumentContainsElementException(argument, DEFAULT_ARGUMENT_NAME, element);
  }

  /**
   * @param element
   * @return the name of the given element.
   */
  private static String getNameOfElement(final Object element) {

    //Handles the case that the given element is null.
    if (element == null) {
      return DEFAULT_ELEMENT_NAME;
    }

    //Handles the case that the given element is not null.
    return element.getClass().getSimpleName();
  }
}
