//package declaration
package ch.nolix.coretest.errorcontroltest.validatortest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnequalArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class GlobalValidatorForObjectTest extends Test {
	
	//method
	@TestCase
	public void testCase_isNotNull() {
		
		//setup
		final var object = "Hocos pocus";
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(object).isNotNull())
		.doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isNotNull_whenTheGivenArgumentIsNull() {
		
		//setup
		final Object object = null;
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(object).isNotNull())
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
		expectRunning(() -> GlobalValidator.assertThat(object).thatIsNamed("test object").isNotNull())
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
		expectRunning(() -> GlobalValidator.assertThat(object).thatIsNamed(Object.class).isNotNull())
		.throwsException()
		.ofType(ArgumentIsNullException.class)
		.withMessage("The given Object is null.");
	}
	
	//method
	@TestCase
	public void testCase_isEqualTo_whenTheGivenArgumentIsEqual() {
		
		//execution
		expectRunning(() -> GlobalValidator.assertThat("garfield").isEqualTo("garfield")).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isEqualTo_whenTheGivenArgumentIsNotEqual() {
		
		//execution
		expectRunning(() -> GlobalValidator.assertThat("garfield").isEqualTo("Garfield"))
		.throwsException()
		.ofType(UnequalArgumentException.class)
		.withMessage("The given String 'garfield' does not equal the String 'Garfield'.");
	}
}
