//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.Value;
import ch.nolix.system.objectdatabase.databaseadapter.NodeDatabaseAdapter;
import ch.nolix.system.objectdatabase.schema.Schema;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;

//class
public final class ValueOnDatabaseTest extends Test {
	
	//static class
	private static final class Pet extends Entity {
		
		//attribute
		public final Value<String> name = new Value<>();
		
		//constructor
		public Pet() {
			initialize();
		}
	}
	
	//method
	@TestCase
	public void testCase_isSaved_whenIsEmpty() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		nodeDatabaseAdapter.insert(garfield);
		
		//execution & verification
		expectRunning(nodeDatabaseAdapter::saveChangesAndReset).throwsException();
	}
	
	//method
	@TestCase
	public void testCase_getOriValue_whenContainsAnyAndIsNotSaved() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		garfield.name.setValue("Garfield");
		nodeDatabaseAdapter.insert(garfield);
		
		//execution
		final var result = garfield.name.getOriValue();
		
		//verification
		expect(result).isEqualTo("Garfield");
	}
	
	//method
	@TestCase
	public void testCase_getOriValue_whenContainsAnyAndIsSaved() {
		
		//setup part 1
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		garfield.name.setValue("Garfield");
		nodeDatabaseAdapter.insert(garfield);
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//setup part 2
		final var loadedGarfield =
		nodeDatabaseAdapter.getOriTableByEntityType(Pet.class).getOriEntityById(garfield.getId());
		
		//execution
		final var result = loadedGarfield.name.getOriValue();
		
		//verification
		expect(result).isEqualTo("Garfield");
	}
	
	//method
	@TestCase
	public void testCase_getState_whenIsNewAndNotEdited() {
		
		//setup
		final var garfield = new Pet();
		
		//setup verification
		expect(garfield.getState()).is(DatabaseObjectState.NEW);
		
		//execution
		final var result = garfield.name.getState();
		
		//verification
		expect(result).is(DatabaseObjectState.NEW);
	}
	
	//method
	@TestCase
	public void testCase_getState_whenIsNewAndEdited() {
		
		//setup
		final var garfield = new Pet();
		garfield.name.setValue("Garfield");
		
		//setup verification
		expect(garfield.getState()).is(DatabaseObjectState.NEW);
		
		//execution
		final var result = garfield.name.getState();
		
		//verification
		expect(result).is(DatabaseObjectState.NEW);
	}
	
	//method
	@TestCase
	public void testCase_getState_whenIsClosed() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		garfield.name.setValue("Garfield");
		nodeDatabaseAdapter.insert(garfield);
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//setup verification
		expect(garfield.getState()).is(DatabaseObjectState.CLOSED);
		
		//execution
		final var result = garfield.name.getState();
		
		//verification
		expect(result).is(DatabaseObjectState.CLOSED);
	}
	
	//method
	@TestCase
	public void testCase_getState_whenIsLoaded() {
		
		//setup part 1
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class);
		final var nodeDatabaseAdapter =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		final var garfield = new Pet();
		garfield.name.setValue("Garfield");
		nodeDatabaseAdapter.insert(garfield);
		nodeDatabaseAdapter.saveChangesAndReset();
		
		//setup part 2
		final var loaedGarfield =
		nodeDatabaseAdapter.getOriTableByEntityType(Pet.class).getOriEntityById(garfield.getId());
		
		//setup verification
		expect(loaedGarfield.getState()).is(DatabaseObjectState.LOADED);
		
		//execution
		final var result = loaedGarfield.name.getState();
		
		//verification
		expect(result).is(DatabaseObjectState.LOADED);
	}
}
