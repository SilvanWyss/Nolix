//package declaration
package ch.nolix.primitive.invalidArgumentException;

//class
/**
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 30
 */
public final class ArgumentName {
	
	//attribute
	private final String value;
	
	//constructor
	/**
	 * Creates new argument name with the given value.
	 * 
	 * @param value
	 * @throws RuntimeException if the given value is null.
	 * @throws RuntimeException if the given value is empty.
	 */
	public ArgumentName(final String value) {
		
		//Checks if the given value is not null.
		if (value == null) {
			throw new RuntimeException("The given argument name is null.");
		}
		
		//Checks if the given value is not empty.
		if (value.isEmpty()) {
			throw new RuntimeException("The given argument name is empty.");
		}
		
		this.value = value;
	}
	
	//method
	/**
	 * @return a string representation of this argument name.
	 */
	public String toString() {
		return value;
	}
}
