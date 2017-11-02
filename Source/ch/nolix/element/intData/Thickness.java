//package declaration
package ch.nolix.element.intData;

//own import
import ch.nolix.element.core.PositiveInteger;

/**
 * A thickness is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 40
 */
public final class Thickness extends PositiveInteger {
	
	//type name
	public static final String TYPE_NAME = "Thickness";
	
	//default value
	public static final int DEFAULT_VALUE = 20;

	//constructor
	/**
	 * Creates new thickness with a default value.
	 */
	public Thickness() {
		
		//Calls constructor of the base class.
		super(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates new thickness with the given value.
	 * 
	 * @param value
	 * @throws NonPositiveArgumentException if the given value is not positive.
	 */
	public Thickness(final int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
