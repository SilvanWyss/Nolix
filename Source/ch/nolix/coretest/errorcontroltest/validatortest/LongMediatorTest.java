//package declaration
package ch.nolix.coretest.errorcontroltest.validatortest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnequalArgumentException;
import ch.nolix.core.errorcontrol.validator.LongMediator;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class LongMediatorTest extends Test {
	
	//method
	@TestCase
	public void testCase_isEqualTo_whenTheGivenArgumentDoesNotEqualTheGivenValue() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("amount", 10);
		
		//execution & verification
		expectRunning(() -> testUnit.isEqualTo(9))
		.throwsException()
		.ofType(UnequalArgumentException.class)
		.withMessage("The given amount '10' does not equal the Integer '9'.");
	}
	
	//method
	@TestCase
	public void testCase_isEqualTo_whenTheGivenArgumentEqualsTheGivenValue() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("amount", 10);
		
		//execution & verification
		expectRunning(() -> testUnit.isEqualTo(10)).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isNotNegative_whenTheGivenArgumentIsNegative() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("amount", -1);
		
		//execution & verification
		expectRunning(testUnit::isNotNegative)
		.throwsException()
		.ofType(NegativeArgumentException.class)
		.withMessage("The given amount '-1' is negative.");
	}
	
	//method
	@TestCase
	public void testCase_isNotNegative_whenTheGivenArgumentIsZero() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("amount", 0);
		
		//execution & verification
		expectRunning(testUnit::isNotNegative).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isNotNegative_whenTheGivenArgumentIsPositive() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("amount", 1);
		
		//execution & verification
		expectRunning(testUnit::isNotNegative).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isPositive_whenTheGivenArgumentIsNegative() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("amount", -1);
		
		//execution & verification
		expectRunning(testUnit::isPositive)
		.throwsException()
		.ofType(NonPositiveArgumentException.class)
		.withMessage("The given amount '-1' is not positive.");
	}
	
	//method
	@TestCase
	public void testCase_isPositive_whenTheGivenArgumentIsZero() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("amount", 0);
		
		//execution & verification
		expectRunning(testUnit::isPositive)
		.throwsException()
		.ofType(NonPositiveArgumentException.class)
		.withMessage("The given amount '0' is not positive.");
	}
	
	//method
	@TestCase
	public void testCase_isPositive_whenTheGivenArgumentIsPositive() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("amount", 1);
		
		//execution & verification
		expectRunning(testUnit::isPositive).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isSmallerThan_whenTheGivenArgumentIsSmallerThanTheGivenValue() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("amount", 10);
		
		//verification & execution
		expectRunning(() -> testUnit.isSmallerThan(20)).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isSmallerThan_whenTheGivenArgumentEqualsTheGivenValue() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("amount", 10);
		
		//verification & execution
		expectRunning(() -> testUnit.isSmallerThan(10))
		.throwsException()
		.ofType(InvalidArgumentException.class)
		.withMessage("The given amount '10' is not smaller than 10.");
	}
	
	//method
	@TestCase
	public void testCase_isSmallerThan_whenTheGivenArgumentIsBiggerThanTheGivenValue() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("amount", 10);
		
		//verification & execution
		expectRunning(() -> testUnit.isSmallerThan(5))
		.throwsException()
		.ofType(InvalidArgumentException.class)
		.withMessage("The given amount '10' is not smaller than 5.");
	}
}
