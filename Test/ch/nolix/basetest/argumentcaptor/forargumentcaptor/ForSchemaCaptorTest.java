/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.forargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.forargumentcaptor.ForNameCaptor;
import ch.nolix.base.argumentcaptor.forargumentcaptor.ForSchemaCaptor;
import ch.nolix.base.foundation.util.VoidObject;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class ForSchemaCaptorTest extends StandardTest {
  @Test
  void testCase_forSchema_whenHasSuccessor() {
    // define test parameters
    final var schema = new VoidObject();

    // setup
    final var forNameCaptor = new ForNameCaptor<>();
    final var testUnit = new ForSchemaCaptor<>(forNameCaptor);

    // execute
    final var result = testUnit.forSchema(schema);

    // verify
    expect(testUnit.getStoredSchema()).is(schema);
    expect(result).is(forNameCaptor);
  }

  @Test
  void testCase_forSchema_whenDoesNotHaveSuccessor() {
    // setup
    final var testUnit = new ForSchemaCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.forSchema(new VoidObject()))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_getStoredSchema_whenDoesNotHaveSchema() {
    // setup
    final var testUnit = new ForSchemaCaptor<>();

    // execute & verify
    expectRunning(testUnit::getStoredSchema).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
