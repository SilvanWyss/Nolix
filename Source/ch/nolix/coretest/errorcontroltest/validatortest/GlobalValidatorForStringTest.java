//package declaration
package ch.nolix.coretest.errorcontroltest.validatortest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class GlobalValidatorForStringTest extends Test {
	
	//method
	@TestCase
	public void testCase_hasLength_whenTheGivenStringDoesNotHaveTheGivenLength() {
		
		//setup
		final var string = "Hocus pocus";
		
		expectRunning(() -> GlobalValidator.assertThat(string).hasLength(200))
		.throwsException()
		.ofType(InvalidArgumentException.class)
		.withMessage("The given argument 'Hocus pocus' does not have the length 200.");
	}
	
	//method
	@TestCase
	public void testCase_hasLength_whenTheGivenStringHasTheGivenLength() {
		
		//setup
		final var string = "Hocus pocus";
		
		expectRunning(() -> GlobalValidator.assertThat(string).hasLength(11))
		.doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isNotBlank_whenTheGivenStringIsBlank() {
		
		//setup
		final var string = " ";
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(string).isNotBlank())
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
		expectRunning(() -> GlobalValidator.assertThat(string).isNotBlank())
		.doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isNotEmpty_whenTheGivenStringIsEmpty() {
		
		//setup
		final var string = "";
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(string).isNotEmpty())
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
		expectRunning(() -> GlobalValidator.assertThat(string).isNotEmpty())
		.doesNotThrowException();
	}
}
