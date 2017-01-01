/*
 * file:	Class.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	30
 */

//package declaration
package ch.nolix.element.data;

import ch.nolix.element.basic.NonEmptyText;

//class
/**
 * A class is a non-empty text.
 */
public final class Token extends NonEmptyText {

	//constructor
	/**
	 * Creates new class with default values.
	 */
	public Token() {}
	
	//constructor
	/**
	 * Creates new class with the given value.
	 * 
	 * @param value
	 * @throws Exception if the given value is null
	 */
	public Token(String value) {
		
		//Calls consturctor of the base class.
		super(value);
	}
}
