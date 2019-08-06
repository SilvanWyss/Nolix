//package declaration
package ch.nolix.coreTest.validatorTest;

import ch.nolix.core.baseTest.TestPool;

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
