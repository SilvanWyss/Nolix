//package declaration
package ch.nolix.element.data;

//own import
import ch.nolix.element.core.NonEmptyText;

//class
/**
 * A name is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 30
 */
public final class Name extends NonEmptyText {
	
	//type name
	public static final String TYPE_NAME = "Name";

	//constructor
	/**
	 * Creates a new name with a default value.
	 */
	public Name() {}
	
	//constructor
	/**
	 * Creates a new name with the given value.
	 * 
	 * @param value
	 * @throws NullArgumentException if the given value is null.
	 * @throws EmptyArgumentException if the given value is empty.
	 */
	public Name(final String value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
