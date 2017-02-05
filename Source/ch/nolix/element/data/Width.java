/*
 * file:	Width.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	30
 */

//package declaration
package ch.nolix.element.data;

import ch.nolix.element.basic.PositiveInteger;

/**
 * A width is a positive integer.
 */
public final class Width extends PositiveInteger {
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "Width";

	//constructor
	/**
	 * Creates new width with default values.
	 */
	public Width() {}
	
	//constructor
	/**
	 * Creates new width with the given value.
	 * 
	 * @param value
	 */
	public Width(int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}