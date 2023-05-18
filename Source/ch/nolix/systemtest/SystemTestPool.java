//package declaration
package ch.nolix.systemtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.databaseapplicationtest.DatabaseApplicationTestPool;
import ch.nolix.systemtest.elementtest.ElementTestPool;
import ch.nolix.systemtest.graphictest.GraphicTestPool;
import ch.nolix.systemtest.guitest.GUITestPool;
import ch.nolix.systemtest.objectdatabasetest.ObjectDatabaseTestPool;
import ch.nolix.systemtest.objectschematest.ObjectSchemaTestPool;
import ch.nolix.systemtest.sqldatabaserawdatatest.SQLDatabaseRawDataTestPool;
import ch.nolix.systemtest.structuretest.StructureTestPool;
import ch.nolix.systemtest.timetest.TimeTestPool;
import ch.nolix.systemtest.webguitest.WebGUITestPool;

//class
public final class SystemTestPool extends TestPool {
	
	//constructor
	public SystemTestPool() {
		super(
			new DatabaseApplicationTestPool(),
			new ElementTestPool(),
			new GraphicTestPool(),
			new GUITestPool(),
			new ObjectDatabaseTestPool(),
			new ObjectSchemaTestPool(),
			new SQLDatabaseRawDataTestPool(),
			new StructureTestPool(),
			new TimeTestPool(),
			new WebGUITestPool()
		);
	}
}
