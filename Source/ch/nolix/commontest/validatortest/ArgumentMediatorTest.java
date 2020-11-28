//package declaration
package ch.nolix.commontest.validatortest;

import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.test.Test;
import ch.nolix.common.validator.Validator;

//class
public final class ArgumentMediatorTest extends Test {
	
	//method
	@TestCase
	public void testCase_isNotNull() {
		
		//setup
		final var object = "Hocos pocus";
		
		//execution & verification
		expect(() -> Validator.assertThat(object).isNotNull())
		.doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isNotNull_whenTheGivenArgumentIsNull() {
		
		//setup
		final Object object = null;
		
		//execution & verification
		expect(() -> Validator.assertThat(object).isNotNull())
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
		expect(() -> Validator.assertThat(object).thatIsNamed("test object").isNotNull())
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
		expect(() -> Validator.assertThat(object).thatIsNamed(Object.class).isNotNull())
		.throwsException()
		.ofType(ArgumentIsNullException.class)
		.withMessage("The given Object is null.");
	}
}
