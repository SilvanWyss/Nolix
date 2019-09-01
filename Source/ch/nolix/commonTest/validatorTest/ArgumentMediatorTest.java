//package declaration
package ch.nolix.commonTest.validatorTest;

import ch.nolix.common.invalidArgumentExceptions.NullArgumentException;
import ch.nolix.common.test.Test;
import ch.nolix.common.validator.Validator;

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
