/*
 * file:	Margin.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	30
 */

//package declaration
package ch.nolix.element.data;

//own import
import ch.nolix.element.basic.PositiveInteger;

/**
 * A margin is a positive integer.
 */
public final class Margin extends PositiveInteger {
	
	//constant
	public final static String SIMPLE_CLASS_NAME = "Margin";

	//constructor
	/**
	 * Creates new margin with default values.
	 */
	public Margin() {}
	
	//constructor
	/**
	 * Creates new margin with the given value.
	 * 
	 * @param value
	 */
	public Margin(int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
