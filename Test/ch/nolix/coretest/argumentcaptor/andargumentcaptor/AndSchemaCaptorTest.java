package ch.nolix.coretest.argumentcaptor.andargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.core.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndSchemaCaptor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.misc.dataobject.VoidObject;
import ch.nolix.core.testing.standardtest.StandardTest;

final class AndSchemaCaptorTest extends StandardTest {

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

  @Test
  void testCase_andSchema_whenDoesNotHaveNext() {

    //setup
    final var testUnit = new AndSchemaCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.andSchema(new VoidObject()))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_getStoredSchema_whenDoesNotHaveSchema() {

    //setup
    final var testUnit = new AndSchemaCaptor<>();

    //execution & verification
    expectRunning(testUnit::getStoredSchema).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
