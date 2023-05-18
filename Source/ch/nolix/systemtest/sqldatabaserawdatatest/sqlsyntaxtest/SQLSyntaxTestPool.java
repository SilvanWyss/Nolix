//package declaration
package ch.nolix.systemtest.sqldatabaserawdatatest.sqlsyntaxtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class SQLSyntaxTestPool extends TestPool {
	
	//constructor
	public SQLSyntaxTestPool() {
		super(
			EntityQueryCreatorTest.class,
			EntityStatementCreatorTest.class
		);
	}
}
