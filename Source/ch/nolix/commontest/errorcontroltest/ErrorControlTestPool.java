//package declaration
package ch.nolix.commontest.errorcontroltest;

//own imports
import ch.nolix.common.basetest.TestPool;
import ch.nolix.commontest.errorcontroltest.validatortest.ValidatorTestPool;

//class
public final class ErrorControlTestPool extends TestPool {
	
	//constructor
	public ErrorControlTestPool() {
		super(new ValidatorTestPool());
	}
}
