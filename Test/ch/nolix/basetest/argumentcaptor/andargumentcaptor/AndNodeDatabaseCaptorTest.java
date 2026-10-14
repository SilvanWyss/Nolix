/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.andargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNodeDatabaseCaptor;
import ch.nolix.base.document.node.MutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class AndNodeDatabaseCaptorTest extends StandardTest {
  @Test
  void testCase_andNodeDatabase_whenHasSuccessor() {
    // define test parameters
    final var nodeDatabase = MutableNode.createEmpty();

    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndNodeDatabaseCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.andNodeDatabase(nodeDatabase);

    // verify
    expect(testUnit.getStoredNodeDatabase()).is(nodeDatabase);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_andNodeDatabase_whenDoesNotHaveSuccessor() {
    // define test parameters
    final var nodeDatabase = MutableNode.createEmpty();

    // setup
    final var testUnit = new AndNodeDatabaseCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.andNodeDatabase(nodeDatabase))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_andTemporaryInMemoryNodeDatabase_whenHasSuccessor() {
    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndNodeDatabaseCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.andTemporaryInMemoryNodeDatabase();

    // verify
    expect(testUnit.getStoredNodeDatabase()).isOfType(MutableNode.class);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_andTemporaryInMemoryNodeDatabase_whenDoesNotHaveSuccessor() {
    // setup
    final var testUnit = new AndNodeDatabaseCaptor<>();

    // execute & verify
    expectRunning(testUnit::andTemporaryInMemoryNodeDatabase)
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_getStoredNodeDatabase_whenDoesNotHaveNodeDatabase() {
    // setup
    final var testUnit = new AndNodeDatabaseCaptor<>();

    // execute & verify
    expectRunning(testUnit::getStoredNodeDatabase)
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
