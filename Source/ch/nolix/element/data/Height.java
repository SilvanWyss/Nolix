//package declaration
package ch.nolix.element.data;

//own import
import ch.nolix.element.basic.PositiveInteger;

/**
 * A height is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 40
 */
public final class Height extends PositiveInteger {
	
	//type name
	public static final String TYPE_NAME = "Height";
	
	//default value
	public static final int DEFAULT_VALUE = 100;

	//constructor
	/**
	 * Creates new height with a default value.
	 */
	public Height() {
		
		//Calls constructor of the base class.
		super(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates new height with the given value.
	 * 
	 * @param value
	 * @throws NonPositiveArgumentException if the given value is not positive.
	 */
	public Height(final int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}