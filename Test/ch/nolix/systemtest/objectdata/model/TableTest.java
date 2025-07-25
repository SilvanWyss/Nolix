package ch.nolix.systemtest.objectdata.model;

import org.junit.jupiter.api.Test;

import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.adapter.NodeDataAdapter;
import ch.nolix.system.objectdata.model.Entity;
import ch.nolix.system.objectdata.model.EntityTypeSet;
import ch.nolix.system.objectdata.model.ValueField;

final class TableTest extends StandardTest {

  private static final class Person extends Entity {

    private final ValueField<String> firstName = ValueField.withValueType(String.class);

    private final ValueField<String> lastName = ValueField.withValueType(String.class);

    void setFirstNameAndLastName(final String firstName, final String lastName) {
      this.firstName.setValue(firstName);
      this.lastName.setValue(lastName);
    }

    String getFirstName() {
      return firstName.getStoredValue();
    }

    String getLastName() {
      return lastName.getStoredValue();
    }
  }

  @Test
  void testCase_getStoredAllEntities_whenIsEmpty() {

    //setup
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var testUnit = nodeDataAdapter.getStoredTableByEntityType(Person.class);

    //execution
    final var result = testUnit.getStoredEntities();

    //verification
    expect(nodeDataAdapter.hasChanges()).isFalse();
    expect(result).isEmpty();
  }

  @Test
  void testCase_getStoredAllEntities() {

    //setup part 1
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = EntityTypeSet.withEntityType(Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var person = new Person();
    person.setFirstNameAndLastName("Donald", "Duck");
    nodeDataAdapter.insertEntity(person);
    nodeDataAdapter.saveChanges();

    //setup part 2
    final var nodeDataAdapter2 = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var testUnit = nodeDataAdapter2.getStoredTableByEntityType(Person.class);

    //execution
    final var result = testUnit.getStoredEntities();

    //verification
    expect(result.getCount()).isEqualTo(1);
    final var loadedPerson = result.getStoredAtOneBasedIndex(1);
    expect(loadedPerson.getId()).isEqualTo(person.getId());
    expect(loadedPerson.getFirstName()).isEqualTo("Donald");
    expect(loadedPerson.getLastName()).isEqualTo("Duck");
  }
}
