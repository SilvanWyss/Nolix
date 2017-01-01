/*
 * file:	Length.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	10
 */

//package declaration
package ch.nolix.element.data;

import ch.nolix.element.basic.PositiveInteger;

/**
 * A length is a positive integer.
 */
public final class Length extends PositiveInteger {
	
	//constant
	public final static String SIMPLE_CLASS_NAME = "Length";

	//constructor
	/**
	 * Creates new length with default values.
	 */
	public Length() {}
	
	//constructor
	/**
	 * Creates new length with the given value.
	 * 
	 * @param value
	 */
	public Length(int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}