//package declaration
package ch.nolix.element.data;

//own import
import ch.nolix.element.color.Color;

//class
/**
 * A scrollbar color is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 50
 */
public final class ScrollbarColor extends Color {

	//constant
	public static final String TYPE_NAME = "ScrollbarColor";
	
	//default value
	public static int DEFAULT_VALUE = Color.GREY_INT;
	
	//constructor
	/**
	 * Creates a new scrollbar color with a default value.
	 */
	public ScrollbarColor() {
		
		//Calls constructor of the base class.
		super(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates a new scrollbar color with the given value.
	 * 
	 * @param value
	 * @throws OutOfRangeArgumentException if the given value is no true color value.
	 */
	public ScrollbarColor(final int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
	
	//constructor
	/**
	 * Creates a new scrollbar color with the given value.
	 * 
	 * @param value
	 * @throws InvalidArgumentException if the given value is no color name or no true color value.
	 */
	public ScrollbarColor(final String value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
