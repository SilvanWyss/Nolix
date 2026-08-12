/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.andargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndSchemaCaptor;
import ch.nolix.base.foundation.util.VoidObject;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class AndSchemaCaptorTest extends StandardTest {
  @Test
  void testCase_andSchema_whenHasSuccessor() {
    // define test parameters
    final var schema = new VoidObject();

    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndSchemaCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.andSchema(schema);

    // verify
    expect(testUnit.getStoredSchema()).is(schema);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_andSchema_whenDoesNotHaveSuccessor() {
    // setup
    final var testUnit = new AndSchemaCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.andSchema(new VoidObject()))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_getStoredSchema_whenDoesNotHaveSchema() {
    // setup
    final var testUnit = new AndSchemaCaptor<>();

    // execute & verify
    expectRunning(testUnit::getStoredSchema).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
