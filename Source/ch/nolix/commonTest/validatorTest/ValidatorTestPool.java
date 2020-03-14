//package declaration
package ch.nolix.commonTest.validatorTest;

//own import
import ch.nolix.common.baseTest.TestPool;

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
