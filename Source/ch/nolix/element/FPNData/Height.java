//package declaration
package ch.nolix.element.FPNData;

//own import
import ch.nolix.element.core.PositiveFloatingPointNumber;

//class
/**
 * A height is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 30
 */
public final class Height extends PositiveFloatingPointNumber {
	
	//constant
	public static final String TYPE_NAME = "Height";

	//constructor
	/**
	 * Creates new height with a default value.
	 */
	public Height() {}
	
	//constructor
	/**
	 * Creates new height with the given value.
	 * 
	 * @param value
	 * @throws NonPositiveArgumentException if the given value is not positive.
	 */
	public Height(final double value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
