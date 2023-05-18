//package declaration
package ch.nolix.systemtest.sqldatabaserawdatatest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.sqldatabaserawdatatest.sqlsyntaxtest.SQLSyntaxTestPool;

//class
public class SQLDatabaseRawDataTestPool extends TestPool {
	
	//constructor
	public SQLDatabaseRawDataTestPool() {
		super(new SQLSyntaxTestPool());
	}
}
