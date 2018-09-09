//package declaration
package ch.nolix.coreTest.databaseAdapterTest;

//own import
import ch.nolix.primitive.testoid.TestPool;

//class
public final class DatabaseAdapterTestPool extends TestPool {

	//constructor
	public DatabaseAdapterTestPool() {
		addTest(new EntityTest());
	}
}
