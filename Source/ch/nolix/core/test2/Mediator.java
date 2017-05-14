//package declaration
package ch.nolix.core.test2;

//own imports
import ch.nolix.core.test.Accessor;
import ch.nolix.core.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 10
 */
abstract class Mediator {

	//attribute
	private final ZetaTest zetaTestest;
	
	//constructor
	/**
	 * Creates new mediator that belongs to the given test.
	 * 
	 * @param zetaTest
	 * @throws NullArgumentException if the given test is null.
	 */
	public Mediator(final ZetaTest zetaTest) {
		
		//Checks if the given test is not null.
		Validator.supposeThat(zetaTest).thatIsNamed("test").isNotNull();
		
		this.zetaTestest = zetaTest;
	}
	
	//method
	/**
	 * Adds the given current test method error to the test this mediator belongs to.
	 */
	protected final void addCurrentTestMethodError(final String error) {
		new Accessor(zetaTestest).addCurrentTestMethodError(error);
	}
	
	protected final ZetaTest getZetaTest() {
		return zetaTestest;
	}
}
