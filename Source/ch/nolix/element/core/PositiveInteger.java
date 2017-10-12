//package declaration
package ch.nolix.element.core;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 30
 */
public class PositiveInteger extends Integeroid {
	
	//type name
	public static final String TYPE_NAME = "PositiveInteger";
	
	//default value
	public static final int DEFAULT_VALUE = 1;
	
	//constructor
	/**
	 * Creates new positive integer with default attributes.
	 */
	public PositiveInteger() {
		
		//Calls constructor of the base class.
		super(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates new positive integer with the given value.
	 * 
	 * @param value
	 * @throws Exception if the given value is not positive
	 */
	public PositiveInteger(int value) {
		super(value);
	}
}
