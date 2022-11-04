//package declaration
package ch.nolix.coretest.errorcontroltest.validatortest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public class ValidatorTestPool extends TestPool {
	
	//constructor
	public ValidatorTestPool() {
		super(
			GlobalValidatorForIntTest.class,
			GlobalValidatorForObjectTest.class,
			GlobalValidatorForStringTest.class
		);
	}
}
