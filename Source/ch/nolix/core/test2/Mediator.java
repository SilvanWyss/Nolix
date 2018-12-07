//package declaration
package ch.nolix.core.test2;

import ch.nolix.core.invalidArgumentException.NullArgumentException;
import ch.nolix.core.testoid.TestAccessor;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 60
 */
public abstract class Mediator {

	//attribute
	private final Test test;
	
	//package-visible constructor
	/**
	 * Creates a new mediator that belongs to the given test.
	 * 
	 * @param test
	 * @throws NullArgumentException if the given test is null.
	 */
	Mediator(final Test test) {
		
		//Checks if the given test is not null.
		if (test == null) {
			throw new NullArgumentException("test");
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
	/**
	 * Adds the given current test case error to the test this mediator belongs to.
	 */
	protected final void addCurrentTestCaseError(final String error) {
		new TestAccessor(test).addCurrentTestCaseError(error);
	}
	
	//method
	/**
	 * @return the test this mediator belongs to.
	 */
	protected final Test getRefTest() {
		return test;
	}
}
