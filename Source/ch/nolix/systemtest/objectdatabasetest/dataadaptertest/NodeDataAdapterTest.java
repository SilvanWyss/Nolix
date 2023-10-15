//package declaration
package ch.nolix.systemtest.objectdatabasetest.dataadaptertest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.exception.ResourceWasChangedInTheMeanwhileException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.schema.Schema;
import ch.nolix.system.objectschema.parameterizedpropertytype.ParameterizedValueType;
import ch.nolix.system.objectschema.schema.Column;
import ch.nolix.system.objectschema.schemaadapter.NodeSchemaAdapter;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;

//class
public final class NodeDataAdapterTest extends Test {

  // static class
  private static final class Pet extends Entity {

    // constructor
    public Pet() {
      initialize();
    }
  }

  // method
  @TestCase
  public void testCase_close_whenIsOpen() {

    // setup
    final var nodeDatabase = new MutableNode();
    @SuppressWarnings("resource")
    final var testUnit = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase")
        .andSchema(Schema.EMPTY_SCHEMA);

    // setup verification
    expect(testUnit.isOpen());

    // execution
    testUnit.close();

    // verification
    expect(testUnit.isClosed());
  }

  // method
  @TestCase
  public void testCase_close_whenIsClosed() {

    // setup
    final var nodeDatabase = new MutableNode();
    @SuppressWarnings("resource")
    final var testUnit = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase")
        .andSchema(Schema.EMPTY_SCHEMA);
    testUnit.close();

    // setup verification
    expect(testUnit.isClosed());

    // execution
    testUnit.close();

    // verification
    expect(testUnit.isClosed());
  }

  // method
  @TestCase
  public void testCase_constructor() {

    // setup
    final var nodeDatabase = new MutableNode();

    // execution
    final var result = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase")
        .andSchema(Schema.EMPTY_SCHEMA);

    // verification
    expect(result.getSaveCount()).isEqualTo(0);
    expect(result.isChangeFree());
  }

  // method
  @TestCase
  public void getEmptyCopy_whenHasChanges() {

    // setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class);
    final var testUnit = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    testUnit.insert(new Pet());

    // setup verification
    expect(testUnit.hasChanges());

    // execution
    final var result = testUnit.getEmptyCopy();

    // verification
    expect(testUnit.hasChanges());
    expect(result.isChangeFree());
  }

  // method
  @TestCase
  public void testCase_saveChangesAndReset_whenDoesNotHaveChanges() {

    // setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class);
    final var testUnit = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);

    // execution
    testUnit.saveChanges();

    // verification
    expect(testUnit.getSaveCount()).isEqualTo(1);
    expect(testUnit.isChangeFree());
  }

  // method
  @TestCase
  public void testCase_saveChangesAndReset_whenHasChanges() {

    // setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class);
    final var testUnit = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    testUnit.insert(new Pet());

    // execution
    testUnit.saveChanges();

    // verification
    expect(testUnit.getSaveCount()).isEqualTo(1);
    expect(testUnit.isChangeFree());
  }

  // method
  @TestCase
  public void testCase_saveChangesAndReset_whenHasChangesAndSchemaWasChangedInTheMeanwhile() {

    // setup part 1: Creates a database.
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class);
    NodeDataAdapter
        .forNodeDatabase(nodeDatabase)
        .withName("my_database")
        .andSchema(schema)
        .saveChanges();

    // setup part 2: Prepare changes for the database.
    final var testUnit = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    testUnit.insert(new Pet());

    // setup part 3: Edit the schema of the database.
    final var schemaAdapter = NodeSchemaAdapter.forDatabaseNode("MyDatabase", nodeDatabase);
    schemaAdapter
        .getStoredTableByName("Pet")
        .addColumn(new Column("Name", new ParameterizedValueType<>(DataType.STRING)));
    schemaAdapter.saveChanges();

    // execution & verification: Try to save the the changes to the database.
    expectRunning(testUnit::saveChanges)
        .throwsException()
        .ofType(ResourceWasChangedInTheMeanwhileException.class)
        .withMessage("The schema was changed in the meanwhile.");
  }
}
