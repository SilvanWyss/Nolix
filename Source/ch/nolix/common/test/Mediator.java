//package declaration
package ch.nolix.common.test;

//own imports
import ch.nolix.common.baseTest.TestAccessor;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotSupportMethodException;
import ch.nolix.common.invalidArgumentException.ArgumentIsNullException;

//class
/**
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 60
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
	 * @throws RuntimeException.
	 */
	@Override
	public final boolean equals(final Object object) {
		throw new RuntimeException(
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
