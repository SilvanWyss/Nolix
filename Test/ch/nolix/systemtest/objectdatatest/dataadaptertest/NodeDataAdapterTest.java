//package declaration
package ch.nolix.systemtest.objectdatatest.dataadaptertest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.exception.ResourceWasChangedInTheMeanwhileException;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdata.schema.Schema;
import ch.nolix.system.objectschema.parameterizedfieldtype.ParameterizedValueType;
import ch.nolix.system.objectschema.schema.Column;
import ch.nolix.system.objectschema.schemaadapter.NodeSchemaAdapter;
import ch.nolix.systemapi.fieldapi.datatypeapi.DataType;

//class
final class NodeDataAdapterTest extends StandardTest {

  //constant
  private static final class Pet extends Entity {

    //constructor
    Pet() {
      initialize();
    }
  }

  //method
  @Test
  void testCase_close_whenIsOpen() {

    //setup
    final var nodeDatabase = new MutableNode();
    @SuppressWarnings("resource")
    final var testUnit = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase")
      .andSchema(Schema.EMPTY_SCHEMA);

    //setup verification
    expect(testUnit.isOpen());

    //execution
    testUnit.close();

    //verification
    expect(testUnit.isClosed());
  }

  //method
  @Test
  void testCase_close_whenIsClosed() {

    //setup
    final var nodeDatabase = new MutableNode();
    @SuppressWarnings("resource")
    final var testUnit = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase")
      .andSchema(Schema.EMPTY_SCHEMA);
    testUnit.close();

    //setup verification
    expect(testUnit.isClosed());

    //execution
    testUnit.close();

    //verification
    expect(testUnit.isClosed());
  }

  //method
  @Test
  void testCase_constructor_whenTheGivenDatabaseIsEmpty() {

    //setup
    final var nodeDatabase = new MutableNode();

    //execution
    final var result = //
    NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(Schema.EMPTY_SCHEMA);

    //verification
    expect(result.getSaveCount()).isEqualTo(0);
    expect(result.isChangeFree());
  }

  //method
  @Test
  void testCase_constructor_whenTheGivenDatabaseIsNotValid() {

    //setup
    final var nodeDatabase = MutableNode.fromString("x(y,z)");

    //execution & verification
    expectRunning(
      () -> NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(Schema.EMPTY_SCHEMA) //
    )
      .throwsException()
      .withMessage("The database has a schema that does not suit.");
  }

  //method
  @Test
  void testCase_getEmptyCopy_whenHasChanges() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class);
    final var testUnit = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    testUnit.insertEntity(new Pet());

    //setup verification
    expect(testUnit.hasChanges());

    //execution
    final var result = testUnit.getEmptyCopy();

    //verification
    expect(testUnit.hasChanges());
    expect(result.isChangeFree());
  }

  //method
  @Test
  void testCase_saveChangesAndReset_whenDoesNotHaveChanges() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class);
    final var testUnit = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);

    //execution
    testUnit.saveChanges();

    //verification
    expect(testUnit.getSaveCount()).isEqualTo(1);
    expect(testUnit.isChangeFree());
  }

  //method
  @Test
  void testCase_saveChangesAndReset_whenHasChanges() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class);
    final var testUnit = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    testUnit.insertEntity(new Pet());

    //execution
    testUnit.saveChanges();

    //verification
    expect(testUnit.getSaveCount()).isEqualTo(1);
    expect(testUnit.isChangeFree());
  }

  //method
  @Test
  void testCase_saveChangesAndReset_whenHasChangesAndSchemaWasChangedInTheMeanwhile() {

    //setup part 1: Creates a database.
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class);
    NodeDataAdapter
      .forNodeDatabase(nodeDatabase)
      .withName("my_database")
      .andSchema(schema)
      .saveChanges();

    //setup part 2: Prepare changes for the database.
    final var testUnit = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    testUnit.insertEntity(new Pet());

    //setup part 3: Edit the schema of the database.
    final var schemaAdapter = NodeSchemaAdapter.forDatabaseNode("MyDatabase", nodeDatabase);
    schemaAdapter
      .getStoredTableByName("Pet")
      .addColumn(new Column("Name", ParameterizedValueType.forDataType(DataType.STRING)));
    schemaAdapter.saveChanges();

    //execution & verification: Try to save the the changes to the database.
    expectRunning(testUnit::saveChanges)
      .throwsException()
      .ofType(ResourceWasChangedInTheMeanwhileException.class)
      .withMessage("The schema was changed in the meanwhile.");
  }
}
