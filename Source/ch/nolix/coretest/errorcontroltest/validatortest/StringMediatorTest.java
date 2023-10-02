//package declaration
package ch.nolix.coretest.errorcontroltest.validatortest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.StringMediator;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class StringMediatorTest extends Test {
	
	//method
	@TestCase
	public void testCase_isNotBlank_whenTheGivenArgumentIsEmpty() {
		
		//setup
		final var testUnit = new StringMediator("");
		
		//execution & verification
		expectRunning(testUnit::isNotBlank)
		.throwsException()
		.ofType(InvalidArgumentException.class);
	}
	
	//method
	@TestCase
	public void testCase_isNotBlank_whenTheGivenArgumentConsistsOfASpace() {
		
		//setup
		final var testUnit = new StringMediator(" ");
		
		//execution & verification
		expectRunning(testUnit::isNotBlank)
		.throwsException()
		.ofType(InvalidArgumentException.class);
	}
	
	//method
	@TestCase
	public void testCase_isNotBlank_whenTheGivenArgumentConsistsOfALetter() {
		
		//setup
		final var testUnit = new StringMediator("a");
		
		//execution & verification
		expectRunning(testUnit::isNotBlank).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isNotLongerThan_whenTheArgumentIsShorterThanTheMaxLength() {
		
		//setup
		final var testUnit = new StringMediator("lorem");
		
		//execution & verification
		expectRunning(() -> testUnit.isNotLongerThan(10)).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isNotLongerThan_whenTheArgumentHasTheMaxLength() {
		
		//setup
		final var testUnit = new StringMediator("lorem ipsu");
		
		//execution & verification
		expectRunning(() -> testUnit.isNotLongerThan(10)).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_isNotLongerThan_whenTheArgumentIsLongerThanTheMaxLength() {
		
		//setup
		final var testUnit = new StringMediator("lorem ipsum dolor");
		
		//execution & verification
		expectRunning(() -> testUnit.isNotLongerThan(10))
		.throwsException()
		.ofType(InvalidArgumentException.class)
		.withMessage("The given argument 'lorem ipsum dolor' is longer than 10.");
	}
}
