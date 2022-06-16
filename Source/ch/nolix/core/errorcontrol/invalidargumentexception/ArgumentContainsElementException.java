//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.core.name.LowerCaseCatalogue;

//class
/**
 * A {@link ArgumentContainsElementException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when an argument contains undesirably a given element.
 * 
 * @author Silvan Wyss
 * @date 2021-07-15
 */
@SuppressWarnings("serial")
public final class ArgumentContainsElementException extends InvalidArgumentException {
	
	//constant
	private static final String DEFAULT_ELEMENT_NAME = LowerCaseCatalogue.ELEMENT;
	
	//static method
	/**
	 * @param element
	 * @return an element name for the given element.
	 */
	private static String getElementName(final Object element) {
		
		//Handles the case that the given element is null.
		if (element == null) {
			return DEFAULT_ELEMENT_NAME;
		}
		
		//Handles the case that the given element is not null.
		return element.getClass().getSimpleName();
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentContainsElementException} for the given argument and element.
	 * 
	 * @param argument
	 * @param element
	 */
	public ArgumentContainsElementException(final Object argument, final Object element) {
		super(argument, "contains already the given " + getElementName(element));
	}
}
