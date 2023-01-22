//package declaration
package ch.nolix.coretest.errorcontroltest.validatortest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class GlobalValidatorForDoubleTest extends Test {
	
	//method
	@TestCase
	public void testCase_isBiggerThan_whenTheValueIsSmaller() {
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(90.0).isBiggerThan(100.0))
		.throwsException()
		.ofType(InvalidArgumentException.class)
		.withMessage("The given argument '90.0' is not bigger than 100.0.");
	}
	
	//method
	@TestCase
	public void testCase_isBiggerThan_whenTheValueEquals() {
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(100.0).isBiggerThan(100.0))
		.throwsException()
		.ofType(InvalidArgumentException.class)
		.withMessage("The given argument '100.0' is not bigger than 100.0.");
	}
	
	//method
	@TestCase
	public void testCase_isBiggerThan_whenTheValueIsBigger() {
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(110.0).isBiggerThan(100.0)).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isNotNegative_whenTheValueIsNegative() {
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(-1.0).isNotNegative())
		.throwsException()
		.ofType(NegativeArgumentException.class)
		.withMessage("The given argument '-1.0' is negative.");
	}
	
	//method
	@TestCase
	public void testCase_isNotNegative_whenTheValueIsZero() {
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(0.0).isNotNegative()).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isNotNegative_whenTheValueIsPositive() {
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(1.0).isNotNegative()).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isPositive_whenTheValueIsNegative() {
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(-1.0).isPositive())
		.throwsException()
		.ofType(NonPositiveArgumentException.class)
		.withMessage("The given argument '-1.0' is not positive.");
	}
	
	//method
	@TestCase
	public void testCase_isPositive_whenTheValueIsZero() {
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(0.0).isPositive())
		.throwsException()
		.ofType(NonPositiveArgumentException.class)
		.withMessage("The given argument '0.0' is not positive.");
	}
	
	//method
	@TestCase
	public void testCase_isPositive_whenTheValueIsPositive() {
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(1.0).isPositive()).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isSmallerThan_whenTheValueIsSmaller() {
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(90.0).isSmallerThan(100.0)).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isSmallerThan_whenTheValueEquals() {
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(100.0).isSmallerThan(100.0))
		.throwsException()
		.ofType(InvalidArgumentException.class)
		.withMessage("The given argument '100.0' is not smaller than 100.0.");
	}
	
	//method
	@TestCase
	public void testCase_isSmallerThan_whenTheValueIsBigger() {
		
		//execution & verification
		expectRunning(() -> GlobalValidator.assertThat(110.0).isSmallerThan(100.0))
		.throwsException()
		.ofType(InvalidArgumentException.class)
		.withMessage("The given argument '110.0' is not smaller than 100.0.");
	}
}
