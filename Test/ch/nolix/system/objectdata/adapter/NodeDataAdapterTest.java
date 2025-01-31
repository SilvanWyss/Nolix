package ch.nolix.system.objectdata.adapter;

import org.junit.jupiter.api.Test;

import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.exception.ResourceWasChangedInTheMeanwhileException;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.system.objectdata.model.Entity;
import ch.nolix.system.objectdata.schemamodel.Schema;
import ch.nolix.system.objectschema.adapter.NodeSchemaAdapter;
import ch.nolix.system.objectschema.model.Column;
import ch.nolix.system.objectschema.model.ValueModel;

final class NodeDataAdapterTest extends StandardTest {

  private static final class Pet extends Entity {

    Pet() {
      initialize();
    }
  }

  @Test
  void testCase_close_whenIsOpen() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var testUnit = //
    NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(Schema.EMPTY_SCHEMA);

    //setup verification
    expect(testUnit.isOpen()).isTrue();

    //execution
    testUnit.close();

    //verification
    expect(testUnit.isClosed()).isTrue();
  }

  @Test
  void testCase_close_whenIsClosed() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var testUnit = //
    NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(Schema.EMPTY_SCHEMA);
    testUnit.close();

    //setup verification
    expect(testUnit.isClosed()).isTrue();

    //execution
    testUnit.close();

    //verification
    expect(testUnit.isClosed()).isTrue();
  }

  @Test
  void testCase_constructor_whenTheGivenDatabaseIsEmpty() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();

    //execution
    final var result = //
    NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(Schema.EMPTY_SCHEMA);

    //verification
    expect(result.getSaveCount()).isEqualTo(0);
    expect(result.isChangeFree()).isTrue();
  }

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

  @Test
  void testCase_getEmptyCopy_whenHasChanges() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = Schema.withEntityType(Pet.class);
    final var testUnit = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    testUnit.insertEntity(new Pet());

    //setup verification
    expect(testUnit.hasChanges()).isTrue();

    //execution
    final var result = testUnit.createEmptyCopy();

    //verification
    expect(testUnit.hasChanges()).isTrue();
    expect(result.isChangeFree()).isTrue();
  }

  @Test
  void testCase_saveChangesAndReset_whenDoesNotHaveChanges() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = Schema.withEntityType(Pet.class);
    final var testUnit = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);

    //execution
    testUnit.saveChanges();

    //verification
    expect(testUnit.getSaveCount()).isEqualTo(1);
    expect(testUnit.isChangeFree()).isTrue();
  }

  @Test
  void testCase_saveChangesAndReset_whenHasChanges() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = Schema.withEntityType(Pet.class);
    final var testUnit = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    testUnit.insertEntity(new Pet());

    //execution
    testUnit.saveChanges();

    //verification
    expect(testUnit.getSaveCount()).isEqualTo(1);
    expect(testUnit.isChangeFree()).isTrue();
  }

  @Test
  void testCase_saveChangesAndReset_whenHasChangesAndSchemaWasChangedInTheMeanwhile() {

    //setup part 1: Creates a database.
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = Schema.withEntityType(Pet.class);
    NodeDataAdapter
      .forNodeDatabase(nodeDatabase)
      .withName("my_database")
      .andSchema(schema)
      .saveChanges();

    //setup part 2: Prepare changes for the database.
    final var testUnit = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    testUnit.insertEntity(new Pet());

    //setup part 4: Edit the schema of the database.
    final var schemaAdapter = NodeSchemaAdapter.forNodeDatabase("MyDatabase", nodeDatabase);
    schemaAdapter
      .getStoredTableByName("Pet")
      .addColumn(new Column("Name", ValueModel.forDataType(DataType.STRING)));
    schemaAdapter.saveChanges();

    //execution & verification: Try to save the the changes to the database.
    expectRunning(testUnit::saveChanges)
      .throwsException()
      .ofType(ResourceWasChangedInTheMeanwhileException.class)
      .withMessage("The schema was changed in the meanwhile.");
  }
}
