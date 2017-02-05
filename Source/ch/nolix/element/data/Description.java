//package declaration
package ch.nolix.element.data;

//own import
import ch.nolix.element.basic.NonEmptyText;

//class
/**
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 40
 */
public final class Description extends NonEmptyText {
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "Description";

	//constructor
	/**
	 * Creates new description with a default value.
	 */
	public Description() {}
	
	//constructor
	/**
	 * Creates new description with the given value.
	 * 
	 * @param value
	 * @throws NullArgumentException if the given value is null.
	 * @throws EmptyArgumentException if the given value is an empty string.
	 */
	public Description(final String value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
