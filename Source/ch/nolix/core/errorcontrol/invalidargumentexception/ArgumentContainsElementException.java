//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
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

  //constant
  private static final String DEFAULT_ELEMENT_NAME = "element";

  //constructor
  /**
   * Creates a new {@link ArgumentContainsElementException} for the given argument
   * and element.
   * 
   * @param argument
   * @param element
   */
  private ArgumentContainsElementException(final Object argument, final Object element) {
    super(argument, "contains already the given " + getNameOfElement(element));
  }

  //static method
  /**
   * @param argument
   * @param element
   * @return a new {@link ArgumentContainsElementException} for the given argument
   *         and element.
   */
  public static ArgumentContainsElementException forArgumentAndElement(final Object argument, final Object element) {
    return new ArgumentContainsElementException(argument, element);
  }

  //static method
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
