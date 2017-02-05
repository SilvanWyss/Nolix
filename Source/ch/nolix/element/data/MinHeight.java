/*
 * file:	MinHeight.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	30
 */

//package declaration
package ch.nolix.element.data;

//own import
import ch.nolix.element.basic.PositiveInteger;

/**
 * A max width is a positive integer.
 */
public final class MinHeight extends PositiveInteger {
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "MinHeight";

	//constructor
	/**
	 * Creates new min height with default values.
	 */
	public MinHeight() {}
	
	//constructor
	/**
	 * Creates new margin with the given value.
	 * 
	 * @param value
	 * @throws Exception if the given value is not positive
	 */
	public MinHeight(final int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
