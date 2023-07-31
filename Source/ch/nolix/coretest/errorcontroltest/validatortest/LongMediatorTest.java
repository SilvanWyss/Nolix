//package declaration
package ch.nolix.coretest.errorcontroltest.validatortest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.LongMediator;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class LongMediatorTest extends Test {
	
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
