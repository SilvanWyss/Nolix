//package declaration
package ch.nolix.coretest.errorcontroltest;

import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coretest.errorcontroltest.validatortest.ValidatorTestPool;

//class
public final class ErrorControlTestPool extends TestPool {
	
	//constructor
	public ErrorControlTestPool() {
		super(new ValidatorTestPool());
	}
}
