//package declaration
package ch.nolix.coreTest.validatorTest;

//own imports
import ch.nolix.core.test.Test;
import ch.nolix.core.validator.Validator;

//test class
public final class TerminalArgumentMediatorTest extends Test {
	
	//test case
	public void testCase_andReturn() {
		
		//setup
		final var string = "Lorem ipsum";
		
		//execution
		final var result = Validator.suppose(string).isNotNull().andReturn();
		
		//verification
		expect(result).isSameAs(string);
	}
}
