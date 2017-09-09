//package declaration
package ch.nolix.element.data;

//own import
import ch.nolix.element.basic.PositiveInteger;

/**
 * A size is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 40
 */
public final class Size extends PositiveInteger {
	
	//type name
	public static final String TYPE_NAME = "Size";
	
	//default value
	public static final int DEFAULT_VALUE = 20;

	//constructor
	/**
	 * Creates new size with a default value.
	 */
	public Size() {
		
		//Calls constructor of the base class.
		super(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates new size with the given value.
	 * 
	 * @param value
	 * @throws NonPositiveArgumentException if the given value is not positive.
	 */
	public Size(final int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
