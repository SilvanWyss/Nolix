//package declaration
package ch.nolix.primitive.invalidArgumentException;

//class
/**
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 40
 */
public final class ErrorPredicate {

	//attribute
	private final String value;
	
	//constructor
	/**
	 * Creates a new error pedicate with the given value.
	 *
	 * @param value
	 * @throws RuntimeException if the given value is not an instance.
	 * @throws RuntimeException if the given value is empty.
	 */
	public ErrorPredicate(final String value) {
		
		//Checks if the given value is an instance.
		if (value == null) {
			throw new RuntimeException("The given error predicate is not an instance.");
		}
		
		//Checks if the given value is not empty.
		if (value.isEmpty()) {
			throw new RuntimeException("The given error predicate is empty.");
		}
		
		this.value = value;
	}
	
	//method
	/**
	 * @return a string representation of this error pedicate.
	 */
	public String toString() {
		return value;
	}
}
