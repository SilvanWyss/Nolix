//package declaration
package ch.nolix.common.zetaValidator;

import ch.nolix.common.exception.NullArgumentException;

//class
/**
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 10
 */
public final class ObjectContainerMediator extends ElementContainerMediator<Object> {

	//package-visible constructor
	/**
	 * Creates new object container mediator with the given arguments.
	 * 
	 * @param arguments
	 * @throws NullArgumentException if the given arguments is null.
	 */
	ObjectContainerMediator(final Iterable<Object> arguments) {
		
		//Calls constructor of the base class.
		super(arguments);
	}
}
