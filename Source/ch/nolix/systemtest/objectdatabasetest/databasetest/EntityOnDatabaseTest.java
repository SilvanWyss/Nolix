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
		public final Value<Integer> ageInYears = new Value<>();
		
		//constructor
		public Pet() {
			initialize();
		}
	}
	
	//method
	@TestCase
	public void testCase_isSaved_whenIsChangedInTheMeanwhile() {
		
		//setup part 1: Initializes database.
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class);
		final var nodeDatabaseAdapterA =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfieldA = new Pet();
		garfieldA.ageInYears.setValue(5);
		nodeDatabaseAdapterA.insert(garfieldA);
		nodeDatabaseAdapterA.saveChangesAndReset();
		
		//setup part 2: Prepares a change.
		final var nodeDatabaseAdapterB =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfieldB =
		nodeDatabaseAdapterB.getRefTableByEntityType(Pet.class).getRefEntityById(garfieldA.getId());
		garfieldB.ageInYears.setValue(6);
		
		//setup part 3: Makes a change.
		final var nodeDatabaseAdapterC =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfieldC =
		nodeDatabaseAdapterC.getRefTableByEntityType(Pet.class).getRefEntityById(garfieldA.getId());
		garfieldC.ageInYears.setValue(6);
		nodeDatabaseAdapterC.saveChangesAndReset();
		
		//execution: Tries to save changes.
		expectRunning(nodeDatabaseAdapterB::saveChangesAndReset)
		.throwsException()
		.ofType(ResourceWasChangedInTheMeanwhileException.class)
		.withMessage("The data was changed in the meanwhile.");
	}
	
	//method
	@TestCase
	public void testCase_isSaved_whenIsDeletedInTheMeanwhile() {
		
		//setup part 1: Initializes database.
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class);
		final var nodeDatabaseAdapterA =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfieldA = new Pet();
		garfieldA.ageInYears.setValue(5);
		nodeDatabaseAdapterA.insert(garfieldA);
		nodeDatabaseAdapterA.saveChangesAndReset();
		
		//setup part 2: Prepares a change.
		final var nodeDatabaseAdapterB =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfieldB =
		nodeDatabaseAdapterB.getRefTableByEntityType(Pet.class).getRefEntityById(garfieldA.getId());
		garfieldB.ageInYears.setValue(6);
		
		//setup part 3: Deletes the Entity.
		final var nodeDatabaseAdapterC =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfieldC =
		nodeDatabaseAdapterC.getRefTableByEntityType(Pet.class).getRefEntityById(garfieldA.getId());
		garfieldC.delete();
		nodeDatabaseAdapterC.saveChangesAndReset();
		
		//execution & verification: Tries to save changes.
		expectRunning(nodeDatabaseAdapterB::saveChangesAndReset)
		.throwsException()
		.ofType(ResourceWasChangedInTheMeanwhileException.class)
		.withMessage("The data was changed in the meanwhile.");
	}

	//method
	@TestCase
	public void testCase_delete_whenIsClosed() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		garfield.ageInYears.setValue(5);
		nodeDatabaseAdapter.insert(garfield);
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//execution & verification
		expectRunning(garfield::delete).throwsException();
	}
}
