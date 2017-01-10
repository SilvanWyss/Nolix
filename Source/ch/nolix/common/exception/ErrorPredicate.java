//package declaration
package ch.nolix.common.exception;

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
	 * Creates new error pedicate with the given value.
	 *
	 * @param value
	 * @throws RuntimeException if the given value is null.
	 * @throws RuntimeException if the given value is empty.
	 */
	public ErrorPredicate(final String value) {
		
		//Checks if the given value is not null.
		if (value == null) {
			throw new RuntimeException("The given error predicate is null.");
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
