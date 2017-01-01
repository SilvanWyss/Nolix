//package declaration
package ch.nolix.common.exception;

//class
/**
 * An element exception is an exception that is indeed to be thrown when a element has not a suitable state for a desired operation.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 70
 */
@SuppressWarnings("serial")
public class ElementException extends RuntimeException {

	//constants
	private final static String DEFAULT_TYPE_NAME = "Element";
	private final static String DEFAULT_PREDICATE = "is not valid";
	
	//attribute
	final Object element;
	
	//static method
	/**
	 * @param predicate - The predicate a valid version is created from.
	 * @return a valid version of the given predicate.
	 */
	private static String createPredicate(final String predicate) {
		
		if (predicate == null || predicate.isEmpty()) {
			return DEFAULT_PREDICATE;
		}
		
		return predicate;
	}
	
	//static method
	/**
	 * @param element - The element a valid type name is created of.
	 * @return a type name of the given element.
	 */
	private static String createTypeName(final Object element) {
		
		if (element == null) {
			return DEFAULT_TYPE_NAME;
		}
		
		return element.getClass().getSimpleName();
	}
	
	//constructor
	/**
	 * Creates new element exception with the given element and predicate.
	 * 
	 * @param element - The element of this element exception.
	 * @param predicate - The predicate the message of this element exception is created from.
	 */
	public ElementException(final Object element, final String predicate) {
		
		//Calls constructor of the base class.
		super(createTypeName(element) + " " + createPredicate(predicate) + ".");
		
		//Sets the element of this element excetpion.
		this.element = element;
	}
	
	//method
	/**
	 * @return the element of this element exception.
	 */
	public final Object getElement() {
		return element;
	}
}
