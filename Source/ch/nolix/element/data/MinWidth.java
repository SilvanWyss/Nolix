//package declaration
package ch.nolix.element.data;

//own import
import ch.nolix.element.core.PositiveInteger;

/**
 * A min width is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 40
 */
public final class MinWidth extends PositiveInteger {
	
	//type name
	public static final String TYPE_NAME = "MinWidth";
	
	//default value
	public static final int DEFAULT_VALUE = 100;

	//constructor
	/**
	 * Creates new min width with a default value.
	 */
	public MinWidth() {
		
		//Calls constructor of the base class.
		super(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates new min width the given value.
	 * 
	 * @param value
	 * @throws NonPositiveArgumentException if the given value is not positive.
	 */
	public MinWidth(final int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
