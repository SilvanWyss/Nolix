//package declaration
package ch.nolix.element.data;

import ch.nolix.element.core.PositiveInteger;

/**
 * A min height is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 40
 */
public final class MinHeight extends PositiveInteger {
	
	//type name
	public static final String TYPE_NAME = "MinHeight";
	
	//default value
	public static final int DEFAULT_VALUE = 100;

	//constructor
	/**
	 * Creates new min height with a default value.
	 */
	public MinHeight() {
		
		//Calls constructor of the base class.
		super(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates new min height the given value.
	 * 
	 * @param value
	 * @throws NonPositiveArgumentException if the given value is not positive.
	 */
	public MinHeight(final int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
