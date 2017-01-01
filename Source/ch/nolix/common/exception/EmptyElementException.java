//package declaration
package ch.nolix.common.exception;

//class
/**
 * An empty element exception is an exception that is intended to be thrown when an element is undesired empty.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 20
 */
@SuppressWarnings("serial")
public final class EmptyElementException extends ElementException {

	//constant
	public final static String PREDICATE = "is empty";

	//constructor
	/**
	 * Creates new empty element exception with the given element.
	 * 
	 * @param element - The element of this empty element exception.
	 */
	public EmptyElementException(Object element) {
		
		//Calls constructor of the base class.
		super(element, PREDICATE);
	}
}
