/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.forargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.forargumentcaptor.ForDatabaseCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class ForDatabaseCaptorTest extends StandardTest {
  @Test
  void testCase_forDatabase_whenDoesNotHaveSuccessor() {
    // setup
    final var testUnit = new ForDatabaseCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.forDatabase("test_database"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_forDatabase_whenHasSuccessor() {
    // define test parameters
    final var database = "test_datbase";

    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ForDatabaseCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.forDatabase(database);

    // verify
    expect(testUnit.getDatabase()).isEqualTo(database);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_getDatabase_whenDoesNotHaveDatabase() {
    // setup
    final var testUnit = new ForDatabaseCaptor<>();

    // execute & verify
    expectRunning(testUnit::getDatabase).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
