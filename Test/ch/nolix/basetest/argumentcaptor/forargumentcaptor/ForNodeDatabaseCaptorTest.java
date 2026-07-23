/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.forargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.forargumentcaptor.ForNodeDatabaseCaptor;
import ch.nolix.base.document.node.MutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class ForNodeDatabaseCaptorTest extends StandardTest {
  @Test
  void testCase_forNodeDatabase_whenDoesNotHaveNext() {
    // setup
    final var database = MutableNode.createEmpty();
    final var testUnit = new ForNodeDatabaseCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.forNodeDatabase(database))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_forNodeDatabase_whenHasNext() {
    // setup
    final var database = MutableNode.createEmpty();
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ForNodeDatabaseCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.forNodeDatabase(database);

    // verify
    expect(testUnit.getStoredNodeDatabase()).isEqualTo(database);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_forTemporaryInMemoryNodeDatabase_whenHasNext() {
    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ForNodeDatabaseCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.forTemporaryInMemoryNodeDatabase();

    // verify
    expect(testUnit.getStoredNodeDatabase()).isOfType(IMutableNode.class);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_getStoredNodeDatabase_whenDoesNotHaveNodeDatabase() {
    // setup
    final var testUnit = new ForNodeDatabaseCaptor<>();

    // execute & verify
    expectRunning(testUnit::getStoredNodeDatabase)
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
