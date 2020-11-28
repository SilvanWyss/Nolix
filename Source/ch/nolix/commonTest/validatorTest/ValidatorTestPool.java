//package declaration
package ch.nolix.commonTest.validatorTest;

import ch.nolix.common.basetest.TestPool;

//class
public class ValidatorTestPool extends TestPool {
	
	//constructor
	public ValidatorTestPool() {
		super(
			ArgumentMediatorTest.class,
			TerminalArgumentMediatorTest.class
		);
	}
}
