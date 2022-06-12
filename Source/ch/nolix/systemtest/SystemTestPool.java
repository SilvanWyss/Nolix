//package declaration
package ch.nolix.systemtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.databaseapplicationtest.DatabaseApplicationTestPool;
import ch.nolix.systemtest.formatelementtest.FormatElementTestPool;
import ch.nolix.systemtest.guitest.GUITestPool;
import ch.nolix.systemtest.objectdatatest.ObjectDataTestPool;
import ch.nolix.systemtest.objectschematest.ObjectSchemaTestPool;
import ch.nolix.systemtest.timetest.TimeTestPool;

//class
public final class SystemTestPool extends TestPool {
	
	//constructor
	public SystemTestPool() {
		super(
			new DatabaseApplicationTestPool(),
			new FormatElementTestPool(),
			new GUITestPool(),
			new ObjectDataTestPool(),
			new ObjectSchemaTestPool(),
			new TimeTestPool()
		);
	}
}
