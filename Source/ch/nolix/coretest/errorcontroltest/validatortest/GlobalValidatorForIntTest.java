//package declaration
package ch.nolix.coretest.errorcontroltest.validatortest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class GlobalValidatorForIntTest extends Test {
	
	//method
	@TestCase
	public void testCase_isBetween_whenTheGivenValueIsSmallerThanTheMinimum() {
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(50).isBetween(100, 200))
		.throwsException()
		.ofType(ArgumentIsOutOfRangeException.class)
		.withMessage("The given argument '50' is not in [100, 200].");
	}
	
	//method
	@TestCase
	public void testCase_isBetween_whenTheGivenValueIsTheMinimum() {
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(100).isBetween(100, 200)).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isBetween_whenTheGivenValueIsTheMaximum() {
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(200).isBetween(100, 200)).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isBetween_whenTheGivenValueIsBiggerThanTheMaximum() {
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(250).isBetween(100, 200))
		.throwsException()
		.ofType(ArgumentIsOutOfRangeException.class)
		.withMessage("The given argument '250' is not in [100, 200].");
	}
	
	//method
	@TestCase
	public void testCase_isNotNegative_whenTheGivenValueIsNegative() {
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(-1).isNotNegative())
		.throwsException()
		.ofType(NegativeArgumentException.class)
		.withMessage("The given argument '-1' is negative.");
	}
	
	//method
	@TestCase
	public void testCase_isNotNegative_whenTheGivenValueIsZero() {
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(0).isNotNegative()).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isNotNegative_whenTheGivenValueIsPositive() {
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(1).isNotNegative()).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isPositive_whenTheGivenValueIsNegative() {
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(-1).isPositive())
		.throwsException()
		.ofType(NonPositiveArgumentException.class)
		.withMessage("The given argument '-1' is not positive.");
	}
	
	//method
	@TestCase
	public void testCase_isPositive_whenTheGivenValueIsZero() {
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(0).isPositive())
		.throwsException()
		.ofType(NonPositiveArgumentException.class)
		.withMessage("The given argument '0' is not positive.");
	}
	
	//method
	@TestCase
	public void testCase_isPositive_whenTheGivenValueIsPositive() {
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(1).isPositive()).doesNotThrowException();
	}
}
