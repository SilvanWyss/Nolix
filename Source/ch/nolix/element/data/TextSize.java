/*
 * file:	TextSize.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	10
 */

//package declaration
package ch.nolix.element.data;

import ch.nolix.element.basic.PositiveInteger;

/**
 * A size is a positive integer.
 */
public final class TextSize extends PositiveInteger {
	
	//constant
		public static final String SIMPLE_CLASS_NAME = "TextSize";

	//constructor
	/**
	 * Creates new size with default values.
	 */
	public TextSize() {}
	
	//constructor
	/**
	 * Creates new size with the given value.
	 * 
	 * @param value
	 */
	public TextSize(int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
