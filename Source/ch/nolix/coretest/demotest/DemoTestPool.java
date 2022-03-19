//package declaration
package ch.nolix.coretest.demotest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class DemoTestPool extends TestPool {
	
	//constructor
	public DemoTestPool() {
		super(BankAccountTest.class, CatTest.class);
	}
}
