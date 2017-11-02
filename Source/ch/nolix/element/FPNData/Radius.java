//package declaration
package ch.nolix.element.FPNData;

//own import
import ch.nolix.element.core.PositiveFloatingPointNumber;

//class
/**
 * A radius is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 30
 */
public final class Radius extends PositiveFloatingPointNumber {

	//constant
	public static final String TYPE_NAME = "Radius";
	
	//constructor
	/**
	 * Creates new radius with a default value.
	 */
	public Radius() {}
	
	//constructor
	/**
	 * Creates new radius with the given value.
	 * 
	 * @param value
	 * @throws NonPositiveArgumentException if the given value is not positive.
	 */
	public Radius(final double value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
