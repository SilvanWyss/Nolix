//package declaration
package ch.nolix.commonTest.validatorTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.test.Test;
import ch.nolix.common.validator.Validator;

//class
public final class TerminalArgumentMediatorTest extends Test {
	
	//method
	@TestCase
	public void testCase_andReturn() {
		
		//setup
		final var string = "Lorem ipsum";
		
		//execution
		final var result = Validator.suppose(string).isNotNull().andReturn();
		
		//verification
		expect(result).isSameAs(string);
	}
}
