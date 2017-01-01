/*
 * file:	Thickness.java
 * author:	Silvan Wyss
 * month:	2016-12
 * lines:	40
 */

//package declaration
package ch.nolix.element.data;

//own import
import ch.nolix.element.basic.PositiveInteger;

//class
/**
 * A thickness is a positive integer.
 * 
 * @author Silvan Wyss
 */
public final class Thickness extends PositiveInteger {

	//constructor
	/**
	 * Creates new thickness with default values.
	 */
	public Thickness() {}
	
	//constructor
	/**
	 * Creates new thickness with the given value.
	 * 
	 * @param value		The value of this thickness.
	 * @throws Exception if the given value is not positive
	 */
	public Thickness(final int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
