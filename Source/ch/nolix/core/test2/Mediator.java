//package declaration
package ch.nolix.core.test2;

//own imports
import ch.nolix.core.testoid.TestAccessor;
import ch.nolix.core.validator2.Validator;

//package-visible abstract class
/**
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 50
 */
abstract class Mediator {

	//attribute
	private final Test test;
	
	//constructor
	/**
	 * Creates new mediator that belongs to the given test.
	 * 
	 * @param test
	 * @throws NullArgumentException if the given test is null.
	 */
	public Mediator(final Test test) {
		
		//Checks if the given test is not null.
		Validator.suppose(test).thatIsInstanceOf(Test.class).isNotNull();
		
		//Sets the test of this mediator.
		this.test = test;
	}
	
	//method
	/**
	 * Adds the given current test method error to the test this mediator belongs to.
	 */
	protected final void addCurrentTestMethodError(final String error) {
		new TestAccessor(test).addCurrentTestMethodError(error);
	}
	
	//method
	/**
	 * @return the test this mediator belongs to.
	 */
	protected final Test getTest() {
		return test;
	}
}
