//package declaration
package ch.nolix.systemtest.objectdatatest.datatest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.data.Value;
import ch.nolix.system.objectdata.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdata.schema.Schema;

//class
public final class TableTest extends Test {

  //constant
  private static final class Person extends Entity {

    //attribute
    private final Value<String> firstName = Value.withValueType(String.class);

    //attribute
    private final Value<String> lastName = Value.withValueType(String.class);

    //method
    public void setFirstNameAndLastName(final String firstName, final String lastName) {
      this.firstName.setValue(firstName);
      this.lastName.setValue(lastName);
    }

    //method
    public String getFirstName() {
      return firstName.getStoredValue();
    }

    //method
    public String getLastName() {
      return lastName.getStoredValue();
    }
  }

  //method
  @TestCase
  public void testCase_getStoredAllEntities_whenIsEmpty() {

    //setup
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var testUnit = nodeDataAdapter.getStoredTableByEntityType(Person.class);

    //execution
    final var result = testUnit.getStoredEntities();

    //verification
    expectNot(nodeDataAdapter.hasChanges());
    expect(result).isEmpty();
  }

  //method
  @TestCase
  public void testCase_getStoredAllEntities() {

    //setup part 1
    final var nodeDatabase = new MutableNode();
    final var schema = Schema.withEntityType(Person.class);
    final var nodeDataAdapter = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var person = new Person();
    person.setFirstNameAndLastName("Donald", "Duck");
    nodeDataAdapter.insert(person);
    nodeDataAdapter.saveChanges();

    //setup part 2
    final var nodeDataAdapter2 = NodeDataAdapter.forNodeDatabase(nodeDatabase).withName("MyDatabase").andSchema(schema);
    final var testUnit = nodeDataAdapter2.getStoredTableByEntityType(Person.class);

    //execution
    final var result = testUnit.getStoredEntities();

    //verification
    expect(result.getElementCount()).isEqualTo(1);
    final var loadedPerson = result.getStoredAt1BasedIndex(1);
    expect(loadedPerson.getId()).isEqualTo(person.getId());
    expect(loadedPerson.getFirstName()).isEqualTo("Donald");
    expect(loadedPerson.getLastName()).isEqualTo("Duck");
  }
}
