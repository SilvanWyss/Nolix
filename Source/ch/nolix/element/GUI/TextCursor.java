/*
 * file:	Cursor.java
 * author:	Silvan Wyss
 * month:	2016
 * lines:	30
 */

//package declaration
package ch.nolix.element.GUI;

import ch.nolix.element.color.Color;

//class
public class TextCursor {

	//attribute
	private Color color = new Color();
	
	//method
	/**
	 * @return the color of this cursor
	 */
	public Color getRefColor() {
		return color;
	}
	
	//method
	/**
	 * Sets the color of this cursor.
	 * @param color
	 * @throws Exception if the given color is no color name or no true color value
	 */
	public final void setColor(String color) {
		this.color = new Color(color);
	}
}
