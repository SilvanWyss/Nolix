//package declaration
package ch.nolix.coretest.errorcontroltest.validatortest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
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
	public void testCase_isBetween_whenTheGivenArgumentIsSmallerThanTheGivenMin() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("value", -20);
		
		//execution & verification
		expectRunning(() -> testUnit.isBetween(0, 100))
		.throwsException()
		.ofType(ArgumentIsOutOfRangeException.class)
		.withMessage("The given value '-20' is not in [0, 100].");
	}
	
	//method
	@TestCase
	public void testCase_isBetween_whenTheGivenArgumentEqualsTheGivenMin() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("value", 0);
		
		//execution & verification
		expectRunning(() -> testUnit.isBetween(0, 100)).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isBetween_whenTheGivenArgumentIsBetweenTheGivenMinAndMax() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("value", 55);
		
		//execution & verification
		expectRunning(() -> testUnit.isBetween(0, 100)).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isBetween_whenTheGivenArgumentEqualsTheGivenMax() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("value", 100);
		
		//execution & verification
		expectRunning(() -> testUnit.isBetween(0, 100)).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isBetween_whenTheGivenArgumentIsBiggerThanTheGivenMax() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("value", 120);
		
		//execution & verification
		expectRunning(() -> testUnit.isBetween(0, 100))
		.throwsException()
		.ofType(ArgumentIsOutOfRangeException.class)
		.withMessage("The given value '120' is not in [0, 100].");
	}
	
	//method
	@TestCase
	public void testCase_isBiggerThan_whenTheGivenArgumentIsSmallerThanTheGivenValue() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("value", 20);
		
		//verification & execution
		expectRunning(() -> testUnit.isBiggerThan(100))
		.throwsException()
		.ofType(InvalidArgumentException.class)
		.withMessage("The given value '20' is not bigger than 100.");
	}
	
	//method
	@TestCase
	public void testCase_isBiggerThan_whenTheGivenArgumentEqualsTheGivenValue() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("value", 100);
		
		//verification & execution
		expectRunning(() -> testUnit.isBiggerThan(100))
		.throwsException()
		.ofType(InvalidArgumentException.class)
		.withMessage("The given value '100' is not bigger than 100.");
	}
	
	//method
	@TestCase
	public void testCase_isBiggerThan_whenTheGivenArgumentIsBiggerThanTheGivenValue() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("value", 120);
		
		//verification & execution
		expectRunning(() -> testUnit.isBiggerThan(100)).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isBiggerThanOrEquals_whenTheGivenArgumentIsSmallerThanTheGivenValue() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("value", 20);
		
		//verification & execution
		expectRunning(() -> testUnit.isBiggerThanOrEquals(100))
		.throwsException()
		.ofType(InvalidArgumentException.class)
		.withMessage("The given value '20' is not bigger than or equal to 100.");
	}
	
	//method
	@TestCase
	public void testCase_isBiggerThanOrEquals_whenTheGivenArgumentEqualsTheGivenValue() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("value", 100);
		
		//verification & execution
		expectRunning(() -> testUnit.isBiggerThanOrEquals(100)).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isBiggerThanOrEquals_whenTheGivenArgumentIsBiggerThanTheGivenValue() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("value", 120);
		
		//verification & execution
		expectRunning(() -> testUnit.isBiggerThanOrEquals(100)).doesNotThrowException();
	}
		
	//method
	@TestCase
	public void testCase_isEqualTo_whenTheGivenArgumenIsBiggerThanTheGivenValue() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("value", 10);
		
		//execution & verification
		expectRunning(() -> testUnit.isEqualTo(9))
		.throwsException()
		.ofType(UnequalArgumentException.class);
	}
	
	//method
	@TestCase
	public void testCase_isEqualTo_whenTheGivenArgumentEqualsTheGivenValue() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("value", 10);
		
		//execution & verification
		expectRunning(() -> testUnit.isEqualTo(10)).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isNotNegative_whenTheGivenArgumentIsNegative() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("value", -1);
		
		//execution & verification
		expectRunning(testUnit::isNotNegative)
		.throwsException()
		.ofType(NegativeArgumentException.class)
		.withMessage("The given value '-1' is negative.");
	}
	
	//method
	@TestCase
	public void testCase_isNotNegative_whenTheGivenArgumentIsZero() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("value", 0);
		
		//execution & verification
		expectRunning(testUnit::isNotNegative).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isNotNegative_whenTheGivenArgumentIsPositive() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("value", 1);
		
		//execution & verification
		expectRunning(testUnit::isNotNegative).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isPositive_whenTheGivenArgumentIsNegative() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("value", -1);
		
		//execution & verification
		expectRunning(testUnit::isPositive)
		.throwsException()
		.ofType(NonPositiveArgumentException.class)
		.withMessage("The given value '-1' is not positive.");
	}
	
	//method
	@TestCase
	public void testCase_isPositive_whenTheGivenArgumentIsZero() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("value", 0);
		
		//execution & verification
		expectRunning(testUnit::isPositive)
		.throwsException()
		.ofType(NonPositiveArgumentException.class)
		.withMessage("The given value '0' is not positive.");
	}
	
	//method
	@TestCase
	public void testCase_isPositive_whenTheGivenArgumentIsPositive() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("value", 1);
		
		//execution & verification
		expectRunning(testUnit::isPositive).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isSmallerThan_whenTheGivenArgumentIsSmallerThanTheGivenValue() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("value", 20);
		
		//verification & execution
		expectRunning(() -> testUnit.isSmallerThan(100)).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isSmallerThan_whenTheGivenArgumentEqualsTheGivenValue() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("value", 100);
		
		//verification & execution
		expectRunning(() -> testUnit.isSmallerThan(100))
		.throwsException()
		.ofType(InvalidArgumentException.class)
		.withMessage("The given value '100' is not smaller than 100.");
	}
	
	//method
	@TestCase
	public void testCase_isSmallerThan_whenTheGivenArgumentIsBiggerThanTheGivenValue() {
		
		//setup
		final var testUnit = LongMediator.forArgumentNameAndArgument("value", 120);
		
		//verification & execution
		expectRunning(() -> testUnit.isSmallerThan(100))
		.throwsException()
		.ofType(InvalidArgumentException.class)
		.withMessage("The given value '120' is not smaller than 100.");
	}
}
