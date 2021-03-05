//package declaration
package ch.nolix.commontest.demotest;

//own import
import ch.nolix.common.basetest.TestPool;

//class
public final class DemoTestPool extends TestPool {
	
	//constructor
	public DemoTestPool() {
		super(BankAccountTest.class, CatTest.class);
	}
}
