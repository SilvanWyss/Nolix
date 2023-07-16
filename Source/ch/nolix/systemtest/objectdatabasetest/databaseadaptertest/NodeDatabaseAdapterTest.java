//package declaration
package ch.nolix.systemtest.objectdatabasetest.databaseadaptertest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.exception.ResourceWasChangedInTheMeanwhileException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.databaseadapter.NodeDatabaseAdapter;
import ch.nolix.system.objectdatabase.schema.Schema;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedValueType;
import ch.nolix.system.objectschema.schema.Column;
import ch.nolix.system.objectschema.schemaadapter.NodeSchemaAdapter;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;

//class
public final class NodeDatabaseAdapterTest extends Test {
	
	//static class
	private static final class Pet extends Entity {
		
		//constructor
		public Pet() {
			initialize();
		}
	}
	
	//method
	@TestCase
	public void testCase_constructor() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType();
		
		//execution
		final var result =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		
		//verification
		expect(result.getSaveCount()).isEqualTo(0);
		expect(result.isChangeFree());
	}
	
	//method
	@TestCase
	public void getEmptyCopy_whenHasChanges() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class);
		final var testUnit =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		testUnit.insert(new Pet());
		
		//setup verification
		expect(testUnit.hasChanges());
		
		//execution
		final var result = testUnit.getEmptyCopy();
		
		//verification
		expect(testUnit.hasChanges());
		expect(result.isChangeFree());
	}
	
	//method
	@TestCase
	public void testCase_saveChangesAndReset_whenDoesNotHaveChanges() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class);
		final var testUnit =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		
		//execution
		testUnit.saveChanges();
		
		//verification
		expect(testUnit.getSaveCount()).isEqualTo(1);
		expect(testUnit.isChangeFree());
	}
	
	//method
	@TestCase
	public void testCase_saveChangesAndReset_whenHasChanges() {
		
		//setup
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class);
		final var testUnit =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		testUnit.insert(new Pet());
		
		//execution
		testUnit.saveChanges();
		
		//verification
		expect(testUnit.getSaveCount()).isEqualTo(1);
		expect(testUnit.isChangeFree());
	}
	
	//method
	@TestCase
	public void testCase_saveChangesAndReset_whenHasChangesAndSchemaWasChangedInTheMeanwhile() {
		
		//setup part 1: Creates a database.
		final var nodeDatabase = new MutableNode();
		final var schema = Schema.withEntityType(Pet.class);
		NodeDatabaseAdapter
		.forNodeDatabase(nodeDatabase)
		.withName("my_database")
		.usingSchema(schema)
		.saveChanges();
		
		//setup part 2: Prepare changes for the database.
		final var testUnit =
		NodeDatabaseAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").usingSchema(schema);
		testUnit.insert(new Pet());
		
		//setup part 3: Edit the schema of the database.
		final var schemaAdapter = NodeSchemaAdapter.forDatabaseNode("MyDatabase", nodeDatabase);
		schemaAdapter
		.getStoredTableByName("Pet")
		.addColumn(new Column("Name", new ParametrizedValueType<>(DataType.STRING)));
		schemaAdapter.saveChanges();
		
		//execution & verification: Try to save the the changes to the database.
		expectRunning(testUnit::saveChanges)
		.throwsException()
		.ofType(ResourceWasChangedInTheMeanwhileException.class)
		.withMessage("The schema was changed in the meanwhile.");
	}
}
