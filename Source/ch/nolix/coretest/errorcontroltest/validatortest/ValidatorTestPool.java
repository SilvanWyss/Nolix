//package declaration
package ch.nolix.coretest.errorcontroltest.validatortest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class ValidatorTestPool extends TestPool {
	
	//constructor
	public ValidatorTestPool() {
		super(LongMediatorTest.class, StringMediatorTest.class);
	}
}
