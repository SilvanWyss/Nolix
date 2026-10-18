/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.withargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.withargumentcaptor.WithDatabaseCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class WithDatabaseCaptorTest extends StandardTest {
  @Test
  void testCase_getDatabase_whenDoesNotHaveDatabase() {
    // setup
    final var testUnit = new WithDatabaseCaptor<>();

    // execute & verify
    expectRunning(testUnit::getDatabase).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_withDatabase_whenDoesNotHaveSuccessor() {
    // setup
    final var testUnit = new WithDatabaseCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.withDatabase("test_database"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_withDatabase_whenHasSuccessor() {
    // define test parameters
    final var database = "test_database";

    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new WithDatabaseCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.withDatabase(database);

    // verify
    expect(testUnit.getDatabase()).isEqualTo(database);
    expect(result).is(andNameCaptor);
  }
}
