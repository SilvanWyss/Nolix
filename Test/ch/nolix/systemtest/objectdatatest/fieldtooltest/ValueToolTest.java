//package declaration
package ch.nolix.systemtest.objectdatatest.fieldtooltest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.data.Value;
import ch.nolix.system.objectdata.dataadapter.NodeDataAdapter;
import ch.nolix.system.objectdata.fieldtool.ValueTool;
import ch.nolix.system.objectdata.schema.Schema;

//class
final class ValueToolTest extends StandardTest {

  //constant
  private static class Pet extends Entity {

    //attribute
    private final Value<String> name = Value.withValueType(String.class);

    //constructor
    public Pet() {
      initialize();
    }
  }

  //method
  @Test
  void testCase_canSetValue() {

    //setup
    final var pet = new Pet();
    final String valueToSet = "Garfield";
    final var testUnit = new ValueTool();

    //execution
    final var result = testUnit.canSetValue(pet.name, valueToSet);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_canSetValue_whenTheGivenValueIsClosed() {

    //setup
    final var pet = new Pet();
    final var databaseAdapter = NodeDataAdapter.forTemporaryInMemoryDatabase().withName("my_database")
      .andSchema(Schema.withEntityType(Pet.class));
    databaseAdapter.insertEntity(pet);
    databaseAdapter.close();
    final String valueToSet = "Garfield";
    final var testUnit = new ValueTool();

    //setup verification
    expect(pet.name.isClosed());

    //execution
    final var result = testUnit.canSetValue(pet.name, valueToSet);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_canSetValue_whenTheGivenValueToSetIsNull() {

    //setup
    final var pet = new Pet();
    final String valueToSet = null;
    final var testUnit = new ValueTool();

    //execution
    final var result = testUnit.canSetValue(pet.name, valueToSet);

    //verification
    expectNot(result);
  }
}
