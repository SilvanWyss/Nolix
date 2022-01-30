//package declaration
package ch.nolix.commontest.errorcontroltest;

import ch.nolix.commontest.errorcontroltest.validatortest.ValidatorTestPool;
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class ErrorControlTestPool extends TestPool {
	
	//constructor
	public ErrorControlTestPool() {
		super(new ValidatorTestPool());
	}
}
