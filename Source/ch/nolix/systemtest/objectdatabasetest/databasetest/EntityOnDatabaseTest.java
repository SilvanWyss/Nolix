//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.exception.ResourceWasChangedInTheMeanwhileException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.Schema;
import ch.nolix.system.objectdatabase.database.Value;
import ch.nolix.system.objectdatabase.databaseadapter.NodeDatabaseAdapter;

//class
public final class EntityOnDatabaseTest extends Test {
	
	//static class
	private static final class Pet extends Entity {
		
		//attribute
		private final Value<Integer> ageInYears = new Value<>();
		
		//constructor
		public Pet() {
			initialize();
		}
		
		//method
		public void setAgeInYears(final int ageInYears) {
			this.ageInYears.setValue(ageInYears);
		}
	}
	
	//method
	@TestCase
	public void testCase_whenTriesToSaveAnOutdatedChange() {
		
		//setup part 1: Initializes a database.
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		garfield.setAgeInYears(5);
		nodeDatabaseAdapter.insert(garfield);
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//setup part 2: Prepares a change.
		final var nodeDatabaseAdapter2 =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield2 =
		nodeDatabaseAdapter2.getRefTableByEntityType(Pet.class).getRefEntityById(garfield.getId());
		garfield2.setAgeInYears(6);
		
		//setup part 3: Makes a change.
		final var nodeDatabaseAdapter3 =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield3 =
		nodeDatabaseAdapter3.getRefTableByEntityType(Pet.class).getRefEntityById(garfield.getId());
		garfield3.setAgeInYears(6);
		nodeDatabaseAdapter3.saveChangesAndReset();
		
		//execution: Tries to save the outdated change.
		expectRunning(nodeDatabaseAdapter2::saveChangesAndReset)
		.throwsException()
		.ofType(ResourceWasChangedInTheMeanwhileException.class)
		.withMessage("The data was changed in the meanwhile.");
	}
}
