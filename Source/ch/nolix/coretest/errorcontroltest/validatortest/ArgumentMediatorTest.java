//package declaration
package ch.nolix.coretest.errorcontroltest.validatortest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

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
