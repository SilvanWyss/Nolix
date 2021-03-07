//package declaration
package ch.nolix.systemtest.databasetest;

import ch.nolix.common.testing.basetest.TestPool;
import ch.nolix.systemtest.databasetest.datatypetest.DataTypeTestPool;
import ch.nolix.systemtest.databasetest.entitytest.EntityTestPool;

//class
public final class DatabaseTestPool extends TestPool {
	
	//constructor
	public DatabaseTestPool() {
		super(new DataTypeTestPool(), new EntityTestPool());
	}
}
