package ch.nolix.systemtest.objectdata.model;

import org.junit.jupiter.api.Test;

import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.adapter.NodeDataAdapter;
import ch.nolix.system.objectdata.model.Entity;
import ch.nolix.system.objectdata.model.EntityTypeSet;
import ch.nolix.system.objectdata.model.OptionalValueField;

final class OptionalValueOnDatabaseTest extends StandardTest {

  private static final class Pet extends Entity {

    private final OptionalValueField<String> name = OptionalValueField.withValueType(String.class);

    Pet() {
      initialize();
    }

    String getName() {
      return name.getStoredValue();
    }

    boolean hasName() {
      return name.containsAny();
    }

    void setName(final String name) {
      this.name.setValue(name);
    }
  }

  @Test
  void testCase_whenIsEmptyAndSaved() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    nodeDataAdapter.insertEntity(garfield);

    //execution
    nodeDataAdapter.saveChanges();

    //verification
    final var loadedGarfield = nodeDataAdapter.getStoredTableByEntityType(Pet.class)
      .getStoredEntityById(garfield.getId());
    expect(loadedGarfield.hasName()).isFalse();
  }

  @Test
  void testCase_getStoredValue_whenContainsAnyAndIsNotSaved() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    garfield.setName("Garfield");
    nodeDataAdapter.insertEntity(garfield);

    //execution
    final var result = garfield.getName();

    //verification
    expect(result).isEqualTo("Garfield");
  }

  @Test
  void testCase_getStoredValue_whenContainsAnyAndIsSaved() {

    //setup part 1
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Pet.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var garfield = new Pet();
    garfield.setName("Garfield");
    nodeDataAdapter.insertEntity(garfield);
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
