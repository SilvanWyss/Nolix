//package declaration
package ch.nolix.element.data;

//own import
import ch.nolix.element.basic.PositiveInteger;

/**
 * A text size is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 40
 */
public final class TextSize extends PositiveInteger {
	
	//type name
	public static final String TYPE_NAME = "TextSize";
	
	//default value
	public static final int DEFAULT_VALUE = 20;

	//constructor
	/**
	 * Creates new text size with a default value.
	 */
	public TextSize() {
		
		//Calls constructor of the base class.
		super(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates new text size with the given value.
	 * 
	 * @param value
	 * @throws NonPositiveArgumentException if the given value is not positive.
	 */
	public TextSize(final int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
