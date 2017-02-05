/*
 * file:	Height.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	30
 */

//package declaration
package ch.nolix.element.data;

import ch.nolix.element.basic.PositiveInteger;

/**
 * A height is a positive integer.
 */
public final class Height extends PositiveInteger {
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "Height";

	//constructor
	/**
	 * Creates new height with default values.
	 */
	public Height() {}
	
	//constructor
	/**
	 * Creates new height with the given value.
	 * 
	 * @param value
	 */
	public Height(int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}