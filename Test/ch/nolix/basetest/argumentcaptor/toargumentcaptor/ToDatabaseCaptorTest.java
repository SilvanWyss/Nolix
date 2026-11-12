/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.argumentcaptor.toargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.base.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.base.argumentcaptor.toargumentcaptor.ToDatabaseCaptor;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;

/**
 * @author Silvan Wyss
 */
final class ToDatabaseCaptorTest extends StandardTest {
  @Test
  void testCase_getDatabase_whenDoesNotHaveDatabase() {
    // setup
    final var testUnit = new ToDatabaseCaptor<>();

    // execute & verify
    expectRunning(testUnit::getDatabase).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_toDatabase_whenDoesNotHaveSuccessor() {
    // setup
    final var testUnit = new ToDatabaseCaptor<>();

    // execute & verify
    expectRunning(() -> testUnit.toDatabase("test_database"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_toDatabase_whenHasSuccessor() {
    // define test parameters
    final var database = "test_database";

    // setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ToDatabaseCaptor<>(andNameCaptor);

    // execute
    final var result = testUnit.toDatabase(database);

    // verify
    expect(testUnit.getDatabase()).isEqualTo(database);
    expect(result).is(andNameCaptor);
  }
}
