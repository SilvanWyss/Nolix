//package declaration
package ch.nolix.commontest.errorcontroltest.validatortest;

import ch.nolix.common.testing.basetest.TestPool;

//class
public class ValidatorTestPool extends TestPool {
	
	//constructor
	public ValidatorTestPool() {
		super(ArgumentMediatorTest.class, StringMediatorTest.class);
	}
}
