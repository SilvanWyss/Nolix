/*
 * file:	BorderableRectangleBorder.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	120
 */

//package declaration
package ch.nolix.element.dialog;

//java import
import java.awt.Graphics;


//own imports








import ch.nolix.element.basic.Color;
import ch.nolix.element.data.Size;

//class
public final class BorderableRectangleBorder {
		
	//default values
	public static final int DEFAULT_SIZE = 1;
	public static final String DEFAULT_COLOR = Color.BLACK_STRING;
	
	//attributes
	private final Size size = new Size();
	private Color color = new Color();
	
	//constructor
	/**
	 * Creates new border with default attributes.
	 */
	public BorderableRectangleBorder() {
		setSize(DEFAULT_SIZE);
		setColor(DEFAULT_COLOR);
	}
		
	//method
	/**
	 * @return the color of this border
	 */
	public final Color getRefColor() {
		return color;
	}
	
	public final Size getRefSize() {
		return size;
	}
	
	//method
	/**
	 * @return the size of this border
	 */
	public final int getSize() {
		return size.getValue();
	}
	
	//method
	/**
	 * Paints this border horizontally using the given graphics.
	 * 
	 * @param graphics
	 * @param length
	 * @param distanceFromLeftPanelBorder
	 * @param distanceFromTopPanelBorder
	 * @throws Exception if the given length is not positive
	 */
	public final void paintHorizontally(
		Graphics graphics,
		int length,
		int distanceFromLeftPanelBorder,
		int distanceFromTopPanelBorder
	) {
		graphics.setColor(color.getJavaColor());
		graphics.fillRect(distanceFromLeftPanelBorder, distanceFromTopPanelBorder, length, getSize());
	}
	
	//method
	/**
	 * Paints this border vertically using the given graphics.
	 * 
	 * @param graphics
	 * @param length
	 * @param distanceFromLeftPanelBorder
	 * @param distanceFromTopPanelBorder
	 * @throws Exception if the given length is not positive
	 */
	public final void paintVertically(
		Graphics graphics,
		int length,
		int distanceFromLeftPanelBorder,
		int distanceFromTopPanelBorder
	) {
		graphics.setColor(color.getJavaColor());
		graphics.fillRect(distanceFromLeftPanelBorder, distanceFromTopPanelBorder, getSize(), length);
	}

	//method
	/**
	 * Sets the color of this border.
	 * 
	 * @param color
	 * @throws Exception if the given color is no color name or no true color value
	 */
	public final void setColor(String color) {
		this.color = new Color(color);
	}
	
	//method
	/**
	 * Sets the size of this border.
	 * 
	 * @param size
	 * @throws Exception if the given size is not positive
	 */
	public final void setSize(int size) {
		this.size.setValue(size);
	}
}
