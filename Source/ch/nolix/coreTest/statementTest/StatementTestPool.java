//package declaration
package ch.nolix.coreTest.statementTest;

//own import
import ch.nolix.core.testoid.TestPool;

//class
public final class StatementTestPool extends TestPool {
	
	//constructor
	public StatementTestPool() {
		addTestClass(StatementTest.class);
	}
}
