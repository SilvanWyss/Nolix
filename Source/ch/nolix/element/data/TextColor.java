/*
 * file:	TextColor.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	10
 */

//package declaration
package ch.nolix.element.data;

//own import
import ch.nolix.element.basic.Color;

//class
public final class TextColor extends Color {

	//constant
	public final static String SIMPLE_CLASS_NAME = "TextColor";
	
	//constructor
	/**
	 * Creates new background color with default values.
	 */
	public TextColor() {		
		setValue(WHITE_STRING);
	}
	
	//constructor
	/**
	 * Creates new background color with the given value.
	 * @param value
	 * @throws Exception if the given value is no color name or no true color value
	 */
	public TextColor(String value) {
		setValue(value);
	}
	
	public TextColor(int value) {
		setValue(value);
	}
	
	//method
	/**
	 * @return a copy of this background color
	 */
	public final TextColor getCopy() {
		try {
			return new TextColor(getStringValue());
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}