//package declaration
package ch.nolix.coreTest.validatorTest;

//own import
import ch.nolix.core.testoid.TestPool;

//class
public class ValidatorTestPool extends TestPool {
	
	//constructor
	public ValidatorTestPool() {
		addTestClass(ArgumentMediatorTest.class);
	}
}
