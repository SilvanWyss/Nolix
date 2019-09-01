//package declaration
package ch.nolix.commonTest.validatorTest;

import ch.nolix.common.test.Test;
import ch.nolix.common.validator.Validator;

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
