//package declaration
package ch.nolix.element.data;

//own import
import ch.nolix.element.core.NonEmptyText;

//class
/**
 * A description is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 30
 */
public final class Description extends NonEmptyText {
	
	//type name
	public static final String TYPE_NAME = "Description";

	//constructor
	/**
	 * Creates a new description with a default value.
	 */
	public Description() {}
	
	//constructor
	/**
	 * Creates a new description with the given value.
	 * 
	 * @param value
	 * @throws NullArgumentException if the given value is null.
	 * @throws EmptyArgumentException if the given value is empty.
	 */
	public Description(final String value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
