//package declaration
package ch.nolix.common.exception;

//own import
import ch.nolix.common.constants.StringManager;

//class
/**
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 60
 */
public final class Argument {

	//default value
	private static final String DEFAULT_ARGUMENT_NAME = "argument";
	
	//attribute
	private final Object value;
	
	//constructor
	/**
	 * Creates new argument with the given value.
	 * 
	 * @param value
	 */
	public Argument(final Object value) {
		this.value = value;
	}
	
	//method
	/**
	 * @return a new argument name of this argument.
	 */
	public String createArgumentName() {
		
		//Handles the case if the value of this argument is null.
		if (value == null) {
			return DEFAULT_ARGUMENT_NAME;
		}
		
		//Handles the case if the value of this argument is not null.
		return value.getClass().getSimpleName();
	}
	
	//method
	/**
	 * @return the value of this argument.
	 */
	public Object getValue() {
		return value;
	}
	
	//method
	/**
	 * @return a string representation of this argument.
	 */
	public String toString() {
		
		//Handles the case if the value of this argument is null.
		if (value == null) {
			return StringManager.NULL_NAME;
		}
		
		//Handles the case if the value of this argument is not null.
		return value.toString();
	}
}
