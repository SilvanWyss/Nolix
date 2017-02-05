/*
 * file:	BackgroundColor.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	80
 */

//package declaration
package ch.nolix.element.data;

//own import
import ch.nolix.element.basic.Color;

//class
public final class BackgroundColor extends Color {

	//constant
	public static final String SIMPLE_CLASS_NAME = "BackgroundColor";
	
	//constructor
	/**
	 * Creates new background color with default values.
	 */
	public BackgroundColor() {		
		setValue(WHITE_STRING);
	}
	
	//constructor
	/**
	 * Creates new background color with the given value.
	 * @param value
	 * @throws Exception if the given value is no color name or no true color value
	 */
	public BackgroundColor(String value) {
		setValue(value);
	}
	
	public BackgroundColor(int value) {
		setValue(value);
	}
	
	//method
	/**
	 * @return a copy of this background color
	 */
	public final BackgroundColor getCopy() {
		try {
			return new BackgroundColor(getStringValue());
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
