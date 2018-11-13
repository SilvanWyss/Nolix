//package declaration
package ch.nolix.core.argument;

//class
/**
 * @author Silvan Wyss
 * @month 2018-11
 * @lines 40
 */
public final class ObjectName {
	
	//attribute
	private final String value;
	
	//constructor
	/**
	 * Creates a new {@link ObjectName} with the given value.
	 * 
	 * @param value
	 * @throws RuntimeException if the given value is null.
	 * @throws RuntimeException if the given value is blank.
	 */
	public ObjectName(final String value) {
		
		//Checks if the given value is not null.
		if (value == null) {
			throw new RuntimeException("The given value is null.");
		}
		
		//Checks if the given value is not blank.
		if (value.isBlank()) {
			throw new RuntimeException("The given value is blank.");
		}
		
		//Sets the value of the current object name.
		this.value = value;
	}
	
	//method
	/**
	 * @return a string representation of the current {@link ObjectName}.
	 */
	public String toString() {
		return value;
	}
}
