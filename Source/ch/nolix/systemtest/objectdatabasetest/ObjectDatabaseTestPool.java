//package declaration
package ch.nolix.systemtest.objectdatabasetest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.objectdatabasetest.databaseadaptertest.DatabaseAdapterTestPool;
import ch.nolix.systemtest.objectdatabasetest.databasetest.DatabaseTestPool;

//class
public final class ObjectDatabaseTestPool extends TestPool {
	
	//constructor
	public ObjectDatabaseTestPool() {
		super(new DatabaseTestPool(), new DatabaseAdapterTestPool());
	}
}
