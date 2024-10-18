package ch.nolix.systemtest.objectdatatest.datatest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.data.Value;
import ch.nolix.system.objectdata.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdata.schema.Schema;

final class TableTest extends StandardTest {

  private static final class Person extends Entity {

    private final Value<String> firstName = Value.withValueType(String.class);

    private final Value<String> lastName = Value.withValueType(String.class);

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
    final var schema = Schema.withEntityType(Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var testUnit = nodeDataAdapter.getStoredTableByEntityType(Person.class);

    //execution
    final var result = testUnit.getStoredEntities();

    //verification
    expectNot(nodeDataAdapter.hasChanges());
    expect(result).isEmpty();
  }

  @Test
  void testCase_getStoredAllEntities() {

    //setup part 1
    final var nodeDatabase = MutableNode.createEmpty();
    final var schema = Schema.withEntityType(Person.class);
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
    final var loadedPerson = result.getStoredAt1BasedIndex(1);
    expect(loadedPerson.getId()).isEqualTo(person.getId());
    expect(loadedPerson.getFirstName()).isEqualTo("Donald");
    expect(loadedPerson.getLastName()).isEqualTo("Duck");
  }
}
