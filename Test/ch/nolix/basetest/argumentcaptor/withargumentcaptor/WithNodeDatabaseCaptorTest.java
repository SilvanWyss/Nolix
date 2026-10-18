/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.withargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.withargumentcaptor.WithNodeDatabaseCaptor;
import ch.nolix.base.document.node.MutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class WithNodeDatabaseCaptorTest extends StandardTest {
  @Test
  void testCase_getStoredNodeDatabase_whenDoesNotHaveNodeDatabase() {
    // setup
    final var testUnit = new WithNodeDatabaseCaptor<>();

    // execute & verify
    expectRunning(testUnit::getStoredNodeDatabase)
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_withNodeDatabase_whenDoesNotHaveSuccessor() {
    // setup
    final var database = MutableNode.createEmpty();
    final var testUnit = new WithNodeDatabaseCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.withNodeDatabase(database))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_withNodeDatabase_whenHasSuccessor() {
    // setup
    final var database = MutableNode.createEmpty();
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new WithNodeDatabaseCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.withNodeDatabase(database);

    // verify
    expect(testUnit.getStoredNodeDatabase()).isEqualTo(database);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_withTemporaryInMemoryNodeDatabase_whenDoesNotHaveSuccessor() {
    // setup
    final var testUnit = new WithNodeDatabaseCaptor<>();

    // execute & verify
    expectRunning(testUnit::withTemporaryInMemoryNodeDatabase)
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_withTemporaryInMemoryNodeDatabase_whenHasSuccessor() {
    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new WithNodeDatabaseCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.withTemporaryInMemoryNodeDatabase();

    // verify
    expect(testUnit.getStoredNodeDatabase()).isOfType(IMutableNode.class);
    expect(result).is(andNameCaptor);
  }
}
