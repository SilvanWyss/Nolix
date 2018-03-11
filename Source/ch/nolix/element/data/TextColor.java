//package declaration
package ch.nolix.element.data;

//own import
import ch.nolix.element.color.Color;

//class
/**
 * A text color is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 50
 */
public final class TextColor extends Color {

	//type name
	public static final String TYPE_NAME = "TextColor";
	
	//default value
	public static final int DEFAULT_VALUE = Color.BLACK_INT;
	
	//constructor
	/**
	 * Creates a new text color with a default value.
	 */
	public TextColor() {
		
		//Calls constructor of the base class.
		super(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates a new text color with the given value.
	 * 
	 * @param value
	 * @throws OutOfRangeArgumentException if the given value is no true color value.
	 */
	public TextColor(final int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
	
	//constructor
	/**
	 * Creates a new text color with the given value.
	 * 
	 * @param value
	 * @throws InvalidArgumentException if the given value is no color name or no true color value.
	 */
	public TextColor(final String value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
