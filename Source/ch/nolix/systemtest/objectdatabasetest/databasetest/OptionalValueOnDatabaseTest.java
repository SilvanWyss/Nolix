//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;
import ch.nolix.system.objectdatabase.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.OptionalValue;
import ch.nolix.system.objectdatabase.schema.Schema;

//class
public final class OptionalValueOnDatabaseTest extends Test {

  //constant
  private static final class Pet extends Entity {

    //attribute
    private final OptionalValue<String> name = new OptionalValue<>();

    //constructor
    public Pet() {
      initialize();
    }

    //method
    public String getName() {
      return name.getStoredValue();
    }

    //method
    public boolean hasName() {
      return name.containsAny();
    }

    //method
    public void setName(final String name) {
      this.name.setValue(name);
    }
  }

  //method
  @TestCase
  public void testCase_whenIsEmptyAndSaved() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insert(garfield);

    //execution
    nodeDataAdapter.saveChanges();

    //verification
    final var loadedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());
    expectNot(loadedGarfield.hasName());
  }

  //method
  @TestCase
  public void testCase_getStoredValue_whenContainsAnyAndIsNotSaved() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    garfield.setName("Garfield");
    nodeDataAdapter.insert(garfield);

    //execution
    final var result = garfield.getName();

    //verification
    expect(result).isEqualTo("Garfield");
  }

  //method
  @TestCase
  public void testCase_getStoredValue_whenContainsAnyAndIsSaved() {

    //setup part 1
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    garfield.setName("Garfield");
    nodeDataAdapter.insert(garfield);
    nodeDataAdapter.saveChanges();

    //setup part 2
    final var loadedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());

    //execution
    final var result = loadedGarfield.getName();

    //verification
    expect(result).isEqualTo("Garfield");
  }
}
