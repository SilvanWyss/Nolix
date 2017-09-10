//package declaration
package ch.nolix.core.baseTest;

//own import
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//class
/**
 * A base test accessor can access a base test.
 * A base test is not made accessible in the normal way
 * because a base test should be accessed only
 * by the definition of an abstract super class of a test.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 50
 */
public final class BaseTestAccessor {

	//attribute
	private final BaseTest test;
	
	//constructor
	/**
	 * Creates new base test accessor for the given test.
	 * 
	 * @param test
	 * @throws NullArgumentException if the given test is null.
	 */
	public BaseTestAccessor(final BaseTest test) {
		
		//Checks if the given test is not null.
		if (test == null) {
			throw new NullArgumentException("test");
		}
		
		//Sets the test of htis base test accessor.
		this.test = test;
	}
	
	//method
	/**
	 * Adds the given current test method error
	 * to the test of this base test accessor.
	 * 
	 * @param currentTestMethodError
	 */
	public void addCurrentTestMethodError(
		final String currentTestMethodError
	) {
		test.addCurrentTestMethodError(currentTestMethodError);
	}
}
