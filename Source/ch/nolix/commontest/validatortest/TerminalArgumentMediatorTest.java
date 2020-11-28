//package declaration
package ch.nolix.commontest.validatortest;

import ch.nolix.common.basetest.TestCase;
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
		final var result = Validator.assertThat(string).isNotNull().andReturn();
		
		//verification
		expect(result).isSameAs(string);
	}
}
