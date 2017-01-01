/*
 * file:	Size.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	10
 */

//package declaration
package ch.nolix.element.data;

import ch.nolix.element.basic.PositiveInteger;

/**
 * A size is a positive integer.
 */
public final class Size extends PositiveInteger {

	//constructor
	/**
	 * Creates new size with default values.
	 */
	public Size() {}
	
	//constructor
	/**
	 * Creates new size with the given value.
	 * 
	 * @param value
	 */
	public Size(int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
