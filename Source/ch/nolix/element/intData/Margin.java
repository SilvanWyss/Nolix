//package declaration
package ch.nolix.element.intData;

//own import
import ch.nolix.element.core.PositiveInteger;

/**
 * A margin is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 40
 */
public final class Margin extends PositiveInteger {
	
	//type name
	public static final String TYPE_NAME = "Margin";
	
	//default value
	public static final int DEFAULT_VALUE = 20;

	//constructor
	/**
	 * Creates new margin with a default value.
	 */
	public Margin() {
		
		//Calls constructor of the base class.
		super(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates new margin with the given value.
	 * 
	 * @param value
	 * @throws NonPositiveArgumentException if the given value is not positive.
	 */
	public Margin(final int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
