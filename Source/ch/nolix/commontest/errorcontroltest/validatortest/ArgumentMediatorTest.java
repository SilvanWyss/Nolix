//package declaration
package ch.nolix.commontest.errorcontroltest.validatortest;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.Test;

//class
public final class ArgumentMediatorTest extends Test {
	
	//method
	@TestCase
	public void testCase_isNotNull() {
		
		//setup
		final var object = "Hocos pocus";
		
		//execution & verification
		expectRunning(() -> Validator.assertThat(object).isNotNull())
		.doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isNotNull_whenTheGivenArgumentIsNull() {
		
		//setup
		final Object object = null;
		
		//execution & verification
		expectRunning(() -> Validator.assertThat(object).isNotNull())
		.throwsException()
		.ofType(ArgumentIsNullException.class)
		.withMessage("The given argument is null.");
	}
	
	//method
	@TestCase
	public void testCase_isNotNull_whenTheGivenArgumentIsNullAndTheNameOfTheArgumentIsGiven() {
		
		//setup
		final Object object = null;
		
		//execution & verification
		expectRunning(() -> Validator.assertThat(object).thatIsNamed("test object").isNotNull())
		.throwsException()
		.ofType(ArgumentIsNullException.class)
		.withMessage("The given test object is null.");
	}
	
	//method
	@TestCase
	public void testCase_isNotNull_whenTheGivenArgumentIsNullAndTheTypeOfTheArgumentIsGiven() {
		
		//setup
		final Object object = null;
		
		//execution & verification
		expectRunning(() -> Validator.assertThat(object).thatIsNamed(Object.class).isNotNull())
		.throwsException()
		.ofType(ArgumentIsNullException.class)
		.withMessage("The given Object is null.");
	}
}
