/*
 * file:	MinWidth.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	30
 */

//package declaration
package ch.nolix.element.data;

//own import
import ch.nolix.element.basic.PositiveInteger;

/**
 * A min width is a positive integer.
 */
public final class MinWidth extends PositiveInteger {
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "MinWidth";

	//constructor
	/**
	 * Creates new min width with default values.
	 */
	public MinWidth() {}
	
	//constructor
	/**
	 * Creates new margin with the given value.
	 * 
	 * @param value
	 * @throws Exception if the given value is not positve
	 */
	public MinWidth(final int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
