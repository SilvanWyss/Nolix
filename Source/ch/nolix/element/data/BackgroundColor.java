//package declaration
package ch.nolix.element.data;

//Java import.
import java.awt.Graphics;

//own import
import ch.nolix.element.basic.Color;

//class
/**
 * A background color is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 80
 */
public final class BackgroundColor extends Color {

	//constant
	public static final String SIMPLE_CLASS_NAME = "BackgroundColor";
	
	//constructor
	/**
	 * Creates new background color with default values.
	 */
	public BackgroundColor() {
		
		//Calls constructor of the base class.
		super(WHITE_STRING);
	}
	
	//constructor
	/**
	 * Creates new background color with the given value.
	 * 
	 * @param value
	 * @throws OutOfRangeArgumentException if the given value is no true color value (in [0, 16'777'215]).
	 */
	public BackgroundColor(final int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
	
	//constructor
	/**
	 * Creates new background color with the given value.
	 * 
	 * @param value
	 * @throws InvalidArgumentException if the given value is no color name or no true color value.
	 */
	public BackgroundColor(final String value) {
		
		//Calls constructor of the base class.
		super(value);
	}
	
	//method
	/**
	 * @return a copy of this background color.
	 */
	public BackgroundColor getCopy() {
		return new BackgroundColor(getValue());
	}
	
	//method
	/**
	 * Lets this background color paint a rectangle using the given graphics.
	 * 
	 * @param graphics
	 * @param distanceFromLeftPanelBorder
	 * @param distanceFromTopPanelBorder
	 * @param width
	 * @param height
	 */
	public void paintRectangle(
		final Graphics graphics,
		final int distanceFromLeftPanelBorder,
		final int distanceFromTopPanelBorder,
		final int width,
		final int height
	) {
		graphics.setColor(getJavaColor());
		graphics.fillRect(distanceFromLeftPanelBorder, distanceFromTopPanelBorder, width, height);
	}
}
