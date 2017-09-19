//package declaration
package ch.nolix.element.data;

import ch.nolix.element.core.PositiveInteger;

/**
 * A width is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 40
 */
public final class Width extends PositiveInteger {
	
	//type name
	public static final String TYPE_NAME = "Width";
	
	//default value
	public static final int DEFAULT_VALUE = 100;

	//constructor
	/**
	 * Creates new width with a default value.
	 */
	public Width() {
		
		//Calls constructor of the base class.
		super(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates new width with the given value.
	 * 
	 * @param value
	 * @throws NonPositiveArgumentException if the given value is not positive.
	 */
	public Width(final int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
