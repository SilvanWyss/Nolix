//package declaration
package ch.nolix.element.data;

//own import
import ch.nolix.element.color.Color;

//class
/**
 * A background color is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 50
 */
public final class BackgroundColor extends Color {

	//type name
	public static final String TYPE_NAME = "BackgroundColor";
	
	//default value
	public static final int DEFAULT_VALUE = Color.BEIGE_INT;
	
	//constructor
	/**
	 * Creates new background color with a default value.
	 */
	public BackgroundColor() {
		
		//Calls constructor of the base class.
		super(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates new background color with the given value.
	 * 
	 * @param value
	 * @throws OutOfRangeArgumentException if the given value is no true color value.
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
}
