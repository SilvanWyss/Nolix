//package declaration
package ch.nolix.core.testoid;

//own import
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//class
/**
 * A test accessor can access a base test.
 * A base test is not made accessible in the normal way
 * because a base test should be accessed only
 * by the definition of an abstract super class of a test.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 50
 */
public final class TestAccessor {

	//attribute
	private final Testoid test;
	
	//constructor
	/**
	 * Creates new test accessor for the given test.
	 * 
	 * @param test
	 * @throws NullArgumentException if the given test is null.
	 */
	public TestAccessor(final Testoid test) {
		
		//Checks if the given test is not null.
		if (test == null) {
			throw new NullArgumentException("test");
		}
		
		//Sets the test of htis test accessor.
		this.test = test;
	}
	
	//method
	/**
	 * Adds the given current test method error
	 * to the test of this test accessor.
	 * 
	 * @param currentTestMethodError
	 */
	public void addCurrentTestMethodError(
		final String currentTestMethodError
	) {
		test.addCurrentTestMethodError(currentTestMethodError);
	}
}
