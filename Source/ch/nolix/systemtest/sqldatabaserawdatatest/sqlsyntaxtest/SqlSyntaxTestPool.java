//package declaration
package ch.nolix.systemtest.sqldatabaserawdatatest.sqlsyntaxtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class SqlSyntaxTestPool extends TestPool {
	
	//constructor
	public SqlSyntaxTestPool() {
		super(
			EntityQueryCreatorTest.class,
			EntityStatementCreatorTest.class
		);
	}
}