//package declaration
package ch.nolix.element.data;

//own import
import ch.nolix.element.basic.Color;

//class
/**
 * A text color is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 60
 */
public final class TextColor extends Color {

	//constant
	public static final String SIMPLE_CLASS_NAME = "TextColor";
	
	//constructor
	/**
	 * Creates new text color with default values.
	 */
	public TextColor() {
		
		//Calls constructor of the base class.
		super(BLACK);
	}
	
	//constructor
	/**
	 * Creates new text color with the given value.
	 * 
	 * @param value
	 * @throws OutOfRangeArgumentException if the given value is no true color value (in [0, 16'777'215]).
	 */
	public TextColor(final int value) {
		
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
	public TextColor(final String value) {
		
		//Calls constructor of the base class.
		super(value);
	}
	
	//method
	/**
	 * @return a copy of this text color.
	 */
	public final TextColor getCopy() {
		return new TextColor(getValue());
	}
}