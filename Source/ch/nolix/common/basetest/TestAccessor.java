//package declaration
package ch.nolix.common.basetest;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;

//class
/**
 * A {@link TestAccessor} can access a {@link BaseTest}.
 * A {@link BaseTest} is not accessible in a common way
 * because it should be accessed only by an abstract base class of a test.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 50
 */
public final class TestAccessor {
	
	//attribute
	private final BaseTest test;
	
	//constructor
	/**
	 * Creates a new {@link TestAccessor} for the given test.
	 * 
	 * @param test
	 * @throws ArgumentIsNullException if the given test is null.
	 */
	public TestAccessor(final BaseTest test) {
		
		//Asserts that the given test is not null.
		if (test == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.TEST);
		}
		
		//Sets the test of the current TestAccessor.
		this.test = test;
	}
	
	//method
	/**
	 * Adds the given expectationError to the test of the current {@link TestAccessor}.
	 * 
	 * @param expectationError
	 */
	public void addExpectationError(final String expectationError) {
		test.addExpectationError(expectationError);
	}
}
