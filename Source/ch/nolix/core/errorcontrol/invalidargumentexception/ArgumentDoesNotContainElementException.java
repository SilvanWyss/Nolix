package ch.nolix.core.errorcontrol.invalidargumentexception;

/**
 * A {@link ArgumentDoesNotContainElementException} is a
 * {@link InvalidArgumentException} that is supposed to be thrown when a given
 * argument does undesirably not contain a given element.
 * 
 * @author Silvan Wyss
 * @version 2021-07-15
 */
@SuppressWarnings("serial")
public final class ArgumentDoesNotContainElementException extends InvalidArgumentException {

  private static final String DEFAULT_ELEMENT_NAME = "element";

  /**
   * Creates a new {@link ArgumentDoesNotContainElementException} for the given
   * argument.
   * 
   * @param argument
   */
  private ArgumentDoesNotContainElementException(final Object argument) {
    super(argument, "does not contain such an element");
  }

  /**
   * Creates a new {@link ArgumentDoesNotContainElementException} for the given
   * argument and element.
   * 
   * @param argument
   * @param element
   */
  private ArgumentDoesNotContainElementException(final Object argument, final Object element) {
    super(argument, "does not contain the given " + getNameForElement(element));
  }

  /**
   * Creates a new {@link ArgumentDoesNotContainElementException} for the given
   * argumentName, argument and element.
   * 
   * @param argumentName
   * @param argument
   * @param element
   */
  private ArgumentDoesNotContainElementException(
    final String argumentName,
    final Object argument,
    final Object element) {
    super(argumentName, argument, "does not contain the given " + getNameForElement(element));
  }

  /**
   * @param argument
   * @return a new {@link ArgumentDoesNotContainElementException} for the given
   *         argument.
   */
  public static ArgumentDoesNotContainElementException forArgument(final Object argument) {
    return new ArgumentDoesNotContainElementException(argument);
  }

  /**
   * @param argument
   * @param element
   * @return a new {@link ArgumentDoesNotContainElementException} for the given
   *         argument and element.
   */
  public static ArgumentDoesNotContainElementException forArgumentAndElement(
    final Object argument,
    final Object element) {
    return new ArgumentDoesNotContainElementException(argument, element);
  }

  /**
   * @param argumentName
   * @param argument
   * @param element
   * @return a new {@link ArgumentDoesNotContainElementException} for the given
   *         argumentName, argument and element.
   */
  public static ArgumentDoesNotContainElementException forArgumentNameAndArgumentAndElement(
    final String argumentName,
    final Object argument,
    final Object element) {
    return new ArgumentDoesNotContainElementException(argumentName, argument, element);
  }

  /**
   * @param element
   * @return the name of the given element.
   */
  private static String getNameForElement(final Object element) {

    //Handles the case that the given element is null.
    if (element == null) {
      return DEFAULT_ELEMENT_NAME;
    }

    //Handles the case that the given element is not null.
    return element.getClass().getSimpleName();
  }
}
