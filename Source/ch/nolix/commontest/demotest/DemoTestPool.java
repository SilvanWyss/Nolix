//package declaration
package ch.nolix.commontest.demotest;

import ch.nolix.common.testing.basetest.TestPool;

//class
public final class DemoTestPool extends TestPool {
	
	//constructor
	public DemoTestPool() {
		super(BankAccountTest.class, CatTest.class);
	}
}
