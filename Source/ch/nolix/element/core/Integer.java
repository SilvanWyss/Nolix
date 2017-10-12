//package declaration
package ch.nolix.element.core;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 30
 */
public final class Integer extends Integeroid {
	
	//type name
	public static final String TYPE_NAME = "Integer";
	
	//default value
	public static final int DEFAULT_VALUE = 0;
	
	//constructor
	/**
	 * Creates a new integer with a default value.
	 */
	public Integer() {
		
		//Calls constructor of the base class.
		super(DEFAULT_VALUE);
	}
		
	//constructor
	/**
	 * Creates a new integer with the given value.
	 * 
	 * @param value
	 */
	public Integer(final int value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
