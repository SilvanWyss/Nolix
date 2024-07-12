//package declaration
package ch.nolix.coretest.argumentcaptortest.andargumentcaptortest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndSchemaCaptor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class AndSchemaCaptorTest extends StandardTest {

  //method
  @Test
  void testCase_andSchema_whenHasNext() {

    //parameter definition
    final var schema = new VoidObject();

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndSchemaCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.andSchema(schema);

    //verification
    expect(testUnit.getStoredSchema()).is(schema);
    expect(result).is(andNameCaptor);
  }

  //method
  @Test
  void testCase_andSchema_whenDoesNotHaveNext() {

    //setup
    final var testUnit = new AndSchemaCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.andSchema(new VoidObject())).throwsException().ofType(InvalidArgumentException.class);
  }

  //method
  @Test
  void testCase_getStoredSchema_whenDoesNotHaveASchema() {

    //setup
    final var testUnit = new AndSchemaCaptor<>();

    //execution & verification
    expectRunning(testUnit::getStoredSchema).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
