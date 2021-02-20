//package declaration
package ch.nolix.systemtest.databasetest;

//own imports
import ch.nolix.common.basetest.TestPool;
import ch.nolix.systemtest.databasetest.datatypetest.DataTypeTestPool;

//class
public final class DatabaseTestPool extends TestPool {
	
	//constructor
	public DatabaseTestPool() {
		super(new DataTypeTestPool());
	}
}
