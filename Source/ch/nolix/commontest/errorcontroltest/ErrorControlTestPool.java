//package declaration
package ch.nolix.commontest.errorcontroltest;

import ch.nolix.common.testing.basetest.TestPool;
import ch.nolix.commontest.errorcontroltest.validatortest.ValidatorTestPool;

//class
public final class ErrorControlTestPool extends TestPool {
	
	//constructor
	public ErrorControlTestPool() {
		super(new ValidatorTestPool());
	}
}
