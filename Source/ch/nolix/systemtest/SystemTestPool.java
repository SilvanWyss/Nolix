//package declaration
package ch.nolix.systemtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.databaseapplicationtest.DatabaseApplicationTestPool;
import ch.nolix.systemtest.elementtest.ElementTestPool;
import ch.nolix.systemtest.graphictest.GraphicTestPool;
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
			new ElementTestPool(),
			new GraphicTestPool(),
			new GUITestPool(),
			new ObjectDataTestPool(),
			new ObjectSchemaTestPool(),
			new TimeTestPool()
		);
	}
}
