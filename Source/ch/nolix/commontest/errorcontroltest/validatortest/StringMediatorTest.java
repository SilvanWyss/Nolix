//package declaration
package ch.nolix.commontest.errorcontroltest.validatortest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.test.Test;

//class
public final class StringMediatorTest extends Test {
	
	//method
	@TestCase
	public void testCase_hasLength_whenTheGivenStringDoesNotHaveTheGivenLength() {
		
		//setup
		final var string = "Hocus pocus";
		
		expectRunning(() -> Validator.assertThat(string).hasLength(200))
		.throwsException()
		.ofType(InvalidArgumentException.class)
		.withMessage("The given argument 'Hocus pocus' does not have the length 200.");
	}
	
	//method
	@TestCase
	public void testCase_hasLength_whenTheGivenStringHasTheGivenLength() {
		
		//setup
		final var string = "Hocus pocus";
		
		expectRunning(() -> Validator.assertThat(string).hasLength(11))
		.doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isNotBlank_whenTheGivenStringIsBlank() {
		
		//setup
		final var string = " ";
		
		//execution & verification
		expectRunning(() -> Validator.assertThat(string).isNotBlank())
		.throwsException()
		.ofType(InvalidArgumentException.class)
		.withMessage("The given argument is blank.");
	}
	
	//method
	@TestCase
	public void testCase_isNotBlank_whenTheGivenStringIsNotBlank() {
		
		//setup
		final var string = "Hocus pocus";
		
		//execution & verification
		expectRunning(() -> Validator.assertThat(string).isNotBlank())
		.doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isNotEmpty_whenTheGivenStringIsEmpty() {
		
		//setup
		final var string = "";
		
		//execution & verification
		expectRunning(() -> Validator.assertThat(string).isNotEmpty())
		.throwsException()
		.ofType(EmptyArgumentException.class)
		.withMessage("The given argument is empty.");
	}
	
	//method
	@TestCase
	public void testCase_isNotEmpty_whenTheGivenStringIsNotEmpty() {
		
		//setup
		final var string = "Hocus pocus";
		
		//execution & verification
		expectRunning(() -> Validator.assertThat(string).isNotEmpty())
		.doesNotThrowException();
	}
}
