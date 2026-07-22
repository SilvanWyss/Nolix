/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.withargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.withargumentcaptor.WithDatabaseCaptor;
import ch.nolix.base.document.node.MutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class WithDatabaseCaptorTest extends StandardTest {
  @Test
  void testCase_getStoredDatabase_whenDoesNotHaveDatabase() {
    // setup
    final var testUnit = new WithDatabaseCaptor<>();

   // execute & verification
    expectRunning(testUnit::getStoredDatabase).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_withDatabase_whenHasNext() {
    // parameter definition
    final var database = MutableNode.createEmpty();

    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new WithDatabaseCaptor<IMutableNode<?>, AndNameCaptor<?>>(andNameCaptor);

   // execute
    final var result = testUnit.withDatabase(database);

   // verify
    expect(testUnit.getStoredDatabase()).is(database);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_withDatabase_whenDoesNotHaveNext() {
    // setup
    final var testUnit = new WithDatabaseCaptor<>();

   // execute & verification
    expectRunning(() -> testUnit.withDatabase(MutableNode.createEmpty()))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
