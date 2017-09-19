//package declaration
package ch.nolix.element.data;

import ch.nolix.element.core.NonEmptyText;

//class
/**
 * A title is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 30
 */
public final class Title extends NonEmptyText {
	
	//type name
	public static final String TYPE_NAME = "Title";

	//constructor
	/**
	 * Creates new title with a default value.
	 */
	public Title() {}
	
	//constructor
	/**
	 * Creates new title with the given value.
	 * 
	 * @param value
	 * @throws NullArgumentException if the given value is null.
	 * @throws EmptyArgumentException if the given value is empty.
	 */
	public Title(final String value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
