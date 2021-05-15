//package declaration
package ch.nolix.common.testing.test;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.testing.basetest.TestAccessor;

//class
/**
 * @author Silvan Wyss
 * @date 2017-01-08
 * @lines 70
 */
public abstract class Mediator {

	//attribute
	private final Test test;
	
	//constructor
	/**
	 * Creates a new mediator that belongs to the given test.
	 * 
	 * @param test
	 * @throws ArgumentIsNullException if the given test is null.
	 */
	Mediator(final Test test) {
		
		//Asserts that the given test is not null.
		if (test == null) {
			throw new ArgumentIsNullException("test");
		}
		
		//Sets the test of this mediator.
		this.test = test;
	}
	
	//method
	/**
	 * @throws IllegalCallerException
	 */
	@Override
	public final boolean equals(final Object object) {
		throw
		new IllegalCallerException(
			"Do not use the equals method of a mediator. For expecting equality, use the 'isEqualTo' method."
		);
	}
	
	//method
	@Override
	public int hashCode() {
		throw new ArgumentDoesNotSupportMethodException(this, "hashCode");
	}
	
	//method
	/**
	 * Adds the given current test case error to the test this mediator belongs to.
	 * 
	 * @param error
	 */
	protected final void addCurrentTestCaseError(final String error) {
		new TestAccessor(test).addExpectationError(error);
	}
	
	//method
	/**
	 * @return the test this mediator belongs to.
	 */
	protected final Test getRefTest() {
		return test;
	}
}
