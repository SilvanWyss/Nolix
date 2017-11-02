//package declaration
package ch.nolix.element.FPNData;

//own import
import ch.nolix.element.core.PositiveFloatingPointNumber;

//class
/**
 * A width is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 30
 */
public final class Width extends PositiveFloatingPointNumber {
	
	//constant
	public static final String TYPE_NAME = "Width";

	//constructor
	/**
	 * Creates new width with a default value.
	 */
	public Width() {}
	
	//constructor
	/**
	 * Creates new width with the given value.
	 * 
	 * @param value
	 * @throws NonPositiveArgumentException if the given value is not positive.
	 */
	public Width(final double value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
