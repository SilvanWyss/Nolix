//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link ArgumentDoesNotContainElementException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument does undesirably not contain a given element.
 * 
 * @author Silvan Wyss
 * @date 2021-07-15
 */
@SuppressWarnings("serial")
public final class ArgumentDoesNotContainElementException extends InvalidArgumentException {
	
	//constant
	private static final String DEFAULT_ELEMENT_NAME = "element";
	
	//static method
	/**
	 * @param argument
	 * @param element
	 * @return a new {@link ArgumentDoesNotContainElementException} for the given argument and element.
	 */
	public static ArgumentDoesNotContainElementException forArgumentAndElement(
		final Object argument,
		final Object element
	) {
		return new ArgumentDoesNotContainElementException(argument, element);
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
	
	//constructor
	/**
	 * Creates a new {@link ArgumentDoesNotContainElementException} for the given argument and element.
	 * 
	 * @param argument
	 * @param element
	 */
	private ArgumentDoesNotContainElementException(final Object argument, final Object element) {
		super(argument, "does not contain the given " + getNameOfElement(element));
	}
}
