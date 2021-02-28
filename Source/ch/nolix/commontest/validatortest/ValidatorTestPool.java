//package declaration
package ch.nolix.commontest.validatortest;

//own import
import ch.nolix.common.basetest.TestPool;

//class
public class ValidatorTestPool extends TestPool {
	
	//constructor
	public ValidatorTestPool() {
		super(ArgumentMediatorTest.class, StringMediatorTest.class);
	}
}
