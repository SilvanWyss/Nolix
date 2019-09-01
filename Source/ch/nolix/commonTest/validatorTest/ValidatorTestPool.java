//package declaration
package ch.nolix.commonTest.validatorTest;

import ch.nolix.common.baseTest.TestPool;

//class
public class ValidatorTestPool extends TestPool {
	
	//constructor
	public ValidatorTestPool() {
		addTestClass(
			ArgumentMediatorTest.class,
			TerminalArgumentMediatorTest.class
		);
	}
}
