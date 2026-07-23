/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.andargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndDatabaseNameCaptor;
import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class AndDatabaseNameCaptorTest extends StandardTest {
  @Test
  void testCase_andDatabase_whenHasSuccessor() {
    // define test parameters
    final var database = "database";

    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndDatabaseNameCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.andDatabase(database);

    // verify
    expect(testUnit.getDatabase()).isEqualTo(database);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_andDatabase_whenDoesNotHaveSuccessor() {
    // setup
    final var testUnit = new AndDatabaseNameCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.andDatabase("database"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_getDatabaseName_whenDoesNotHaveDatabaseName() {
    // setup
    final var testUnit = new AndDatabaseNameCaptor<>();

    // execute & verify
    expectRunning(testUnit::getDatabase).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
