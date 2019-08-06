//package declaration
package ch.nolix.coreTest.statementTest;

import ch.nolix.core.baseTest.TestPool;

//class
public final class StatementTestPool extends TestPool {
	
	//constructor
	public StatementTestPool() {
		addTestClass(StatementTest.class);
	}
}
