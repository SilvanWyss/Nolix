//package declaration
package ch.nolix.element.intData;

//own import
import ch.nolix.element.core.PositiveInteger;

/**
 * A length is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 40
 */
public final class Length extends PositiveInteger {
	
	//type name
	public static final String TYPE_NAME = "Length";

	//default value
	public static final int DEFAULT_VALUE = 100;
	
	//constructor
	/**
	 * Creates a new length with a default value.
	 */
	public Length() {
		
		//Calls constructor of the base class.
		super(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates a new length with the given value.
	 * 
	 * @param value
	 * @throws NonPositiveArgumentException if the given value is not positive.
	 */
	public Length(final int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
