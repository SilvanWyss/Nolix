/*
 * file:	Name.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	30
 */

//package declaration
package ch.nolix.element.data;

import ch.nolix.element.basic.NonEmptyText;

//class
/**
 * A name is a non-empty text.
 */
public final class Name extends NonEmptyText {
	
	public static final String SIMPLE_CLASS_NAME = "Name";

	//constructor
	/**
	 * Creates new name with default values.
	 */
	public Name() {}
	
	//constructor
	/**
	 * Creates new name with the given value.
	 * 
	 * @param value
	 * @throws Exception if the given value is null
	 */
	public Name(String value) {
		
		//Calls consturctor of the base class.
		super(value);
	}
}
