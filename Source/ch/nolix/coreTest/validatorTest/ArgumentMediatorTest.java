//package declaration
package ch.nolix.coreTest.validatorTest;

//own imports
import ch.nolix.core.invalidArgumentException.NullArgumentException;
import ch.nolix.core.test.Test;
import ch.nolix.core.validator2.Validator;

//test class
public final class ArgumentMediatorTest extends Test {
	
	//test case
	public void testCase_isNotNull() {
		
		//setup
		final var object = "Hocos pocus";
		
		//execution & verification
		expect(() -> Validator.suppose(object).isNotNull())
		.doesNotThrowException();
	}
	
	//test case
	public void testCase_isNotNull_whenTheGivenArgumentIsNull() {
		
		//setup
		final Object object = null;
		
		//execution & verification
		expect(() -> Validator.suppose(object).isNotNull())
		.throwsException()
		.ofType(NullArgumentException.class)
		.withMessage("The given argument is null.");
	}
	
	//test case
	public void testCase_isNotNull_whenTheGivenArgumentIsNullAndTheNameOfTheArgumentIsGiven() {
		
		//setup
		final Object object = null;
		
		//execution & verification
		expect(() -> Validator.suppose(object).thatIsNamed("test object").isNotNull())
		.throwsException()
		.ofType(NullArgumentException.class)
		.withMessage("The given test object is null.");
	}
	
	//test case
	public void testCase_isNotNull_whenTheGivenArgumentIsNullAndTheTypeOfTheArgumentIsGiven() {
		
		//setup
		final Object object = null;
		
		//execution & verification
		expect(() -> Validator.suppose(object).thatIsNamed(Object.class).isNotNull())
		.throwsException()
		.ofType(NullArgumentException.class)
		.withMessage("The given Object is null.");
	}
}
