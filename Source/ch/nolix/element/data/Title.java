/*
 * file:	Title.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	30
 */

//package declaration
package ch.nolix.element.data;

import ch.nolix.element.basic.NonEmptyText;

//class
/**
 * A title is a non-empty text.
 */
public final class Title extends NonEmptyText {
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "Title";

	//constructor
	/**
	 * Creates new title with default values.
	 */
	public Title() {}
	
	//constructor
	/**
	 * Creates new title with the given value.
	 * 
	 * @param value
	 * @throws Exception if the given value is null
	 */
	public Title(String value) {
		
		//Calls consturctor of the base class.
		super(value);
	}
}
